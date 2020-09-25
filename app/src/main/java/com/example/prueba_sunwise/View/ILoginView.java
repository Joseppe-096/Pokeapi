package com.example.prueba_sunwise.View;

import android.content.Context;

public interface ILoginView {
    void onResult(Boolean result, int code);
    void onSetProgressBarVisibility(int visibility);
}
