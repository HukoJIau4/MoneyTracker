package com.example.lexel.moneytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lexel.moneytracker.api.BalanceResult;

import java.io.IOException;

public class BalanceFragment extends Fragment {

    TextView balance;
    TextView expenses;
    TextView incomes;
    DiagramView diagram;

    private SwipeRefreshLayout refresh;

    public static BalanceFragment createFragment(){
        return new BalanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        balance = view.findViewById(R.id.balance);
        expenses = view.findViewById(R.id.expenses);
        incomes = view.findViewById(R.id.incomes);
        diagram = view.findViewById(R.id.diagram);

        refresh = view.findViewById(R.id.refresh_balance);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData();
            }
        });

    }

    @Override
     public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            updateData();
        }
    }

    private void updateData() {

        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<BalanceResult>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<BalanceResult> onCreateLoader(int id, Bundle args) {
                return new AsyncTaskLoader<BalanceResult>(getContext()) {
                    @Override
                    public BalanceResult loadInBackground() {
                        try {
                            return ((App) getActivity().getApplicationContext()).getApi().balance().execute().body();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<BalanceResult> loader, BalanceResult data) {
                refresh.setRefreshing(false);
                if (data != null && data.isSuccess()){
                    balance.setText(getString(R.string.price, data.totalIncomes - data.totalExpenses));
                    expenses.setText(getString(R.string.price, data.totalExpenses));
                    incomes.setText(getString(R.string.price, data.totalIncomes));
                    diagram.update(data.totalExpenses, data.totalIncomes);
                }else
                    Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLoaderReset(Loader<BalanceResult> loader) {

            }
        }).forceLoad();
    }
}
