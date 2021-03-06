package com.example.lexel.moneytracker;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lexel.moneytracker.api.Api;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.lexel.moneytracker.Item.TYPE_UNKNOWN;

public class ItemsFragment extends Fragment {

    private static final String TAG = "ItemsFragment";
    private static final int LOADER_ITEMS = 0;



    private static final String KEY_TYPE = "TYPE";
    public static ItemsAdapter adapter;
    public static ActionMode actionMode;

    private String type = TYPE_UNKNOWN;

    private Api api;


    private DialogInterface.OnClickListener OnClickListener;

    public static ItemsFragment createItemsFragment(String type){
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.KEY_TYPE, type);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getArguments().getString(KEY_TYPE, TYPE_UNKNOWN);

        if (type.equals(TYPE_UNKNOWN)){

            throw new IllegalStateException("Unknown Fragment type");
        }

        adapter = new ItemsAdapter();
        api = ((App)getActivity().getApplication()).getApi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        adapter.setListener(new ItemsAdapterListener() {
            @Override
            public void onItemClick(Item item, int position) {
                if(isInActionMode()){
                    toggleSelection(position);

                }
            }

            @Override
            public void onItemLongClick(Item item, int position) {
                if(isInActionMode()){
                    return;
                }
                actionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(actionModeCallback);
                toggleSelection(position);
            }

            private void toggleSelection (int position){
                adapter.toggleSelection(position);

            }

            private boolean isInActionMode(){
                return actionMode != null;
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.startForResult(ItemsFragment.this, type, AddActivity.RC_ADD_ITEM);
            }
        });
        loadItems();

        }


    private void loadItems(){
        getLoaderManager().initLoader(LOADER_ITEMS, null, new LoaderManager.LoaderCallbacks<List<Item>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
                return new AsyncTaskLoader<List<Item>>(getContext()) {
                    @Override
                    public List<Item> loadInBackground() {
                        try {
                            return api.items(type).execute().body();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<List<Item>> loader, List<Item> items) {
                if (items == null) {
                    showError();

                } else {
                    adapter.setItems(items);

                }
            }

            @Override
            public void onLoaderReset(Loader<List<Item>> loader) {

            }
        }).forceLoad();
    }
    private void showError(){
        Toast.makeText(getContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddActivity.RC_ADD_ITEM && resultCode == RESULT_OK){

            Item item = (Item) data.getSerializableExtra(AddActivity.RESULT_ITEM);
            Toast.makeText(getContext(), item.name +" " + item.price, Toast.LENGTH_LONG).show();
        }
    }

    static void  removeSelectedItems() {
        for (int i = adapter.getSelectedItems().size() - 1; i >= 0; i--) {
            adapter.remove(adapter.getSelectedItems().get(i));
        }
    }

    static void  clearSelectedItems(){

            adapter.clearSelections();
        }


    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.items_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_remove:
                        showDialog();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

                adapter.clearSelections();
                actionMode = null;

            }
        };

        private void showDialog(){

            DialogFragment dialog;
            dialog = new ConfirmationDialog();
            dialog.show(getFragmentManager(), "Confirmation");




        }
    }




