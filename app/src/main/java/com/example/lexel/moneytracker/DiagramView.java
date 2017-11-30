package com.example.lexel.moneytracker;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class DiagramView extends View {

    private long expenses;
    private long incomes;

    private Paint expensesPaint = new Paint();
    private Paint incomesPaint = new Paint();




    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        expensesPaint.setColor(getResources().getColor(R.color.balance_expenses));
        incomesPaint.setColor(getResources().getColor(R.color.balance_incomes));

        if (isInEditMode()){
            expenses = 9000;
            incomes = 38000;
        }
    }


    public void update(long expenses, long incomes){
        this.expenses = expenses;
        this.incomes = incomes;
        invalidate();
    }

    @Override
     protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawPieDiagram(canvas);
            } else {
            drawRectDiagram(canvas);
            }
        }

    @Override
     protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                      int width = MeasureSpec.getSize(widthMeasureSpec);

                      setMeasuredDimension(width, width);
            }

             private void drawRectDiagram(Canvas canvas) {
               if (expenses + incomes == 0)
                       return;

                       long max = Math.max(expenses, incomes);
               long expensesHeight = getHeight() * expenses / max;
               long incomeHeight = getHeight() * incomes / max;

                        int w = getWidth() / 4;

                        canvas.drawRect(w / 2, getHeight() - expensesHeight, w * 3 / 2, getHeight(), expensesPaint);
               canvas.drawRect(5 * w / 2, getHeight() - incomeHeight, w * 7 / 2, getHeight(), incomesPaint);
           }

             @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
     private void drawPieDiagram(Canvas canvas) {
                if (expenses + incomes == 0)
                        return;

                       float expenseAngle = 360.f * expenses / (expenses + incomes);
               float incomeAngle = 360.f * incomes / (expenses + incomes);

                        int space = 10; // space between income and expense
                int size = Math.min(getWidth(), getHeight()) - space * 2;
               final int xMargin = (getWidth() - size) / 2, yMargin = (getHeight() - size) / 2;

                        canvas.drawArc(xMargin - space, yMargin, getWidth() - xMargin - space, getHeight() - yMargin, 180 - expenseAngle / 2, expenseAngle, true, expensesPaint);
               canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space, getHeight() - yMargin, 360 - incomeAngle / 2, incomeAngle, true, incomesPaint);
           }
}
