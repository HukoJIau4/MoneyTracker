package com.example.lexel.moneytracker.api;

import android.graphics.Interpolator;




public class BalanceResult extends Result {
        public long totalExpenses;
        public long totalIncomes;

        public BalanceResult(long totalExpenses, long totalIncomes) {
            this.totalExpenses = totalExpenses;
            this.totalIncomes = totalIncomes;
        }
    }

