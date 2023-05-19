package com.example.mypaint;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;
    ImageView imageViewColorPicker;
    ImageView imageViewUndoLastPath;
    ImageView imageViewResetDraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simplePaint = findViewById(R.id.simplePaint);
        imageViewColorPicker = findViewById(R.id.ivColorPicker);
        imageViewUndoLastPath = findViewById(R.id.imageViewUndo);
        imageViewResetDraw = findViewById(R.id.imageViewResetDraw);
        imageViewColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPickerSelectColor(imageViewColorPicker);
            }
        }
        );
        imageViewUndoLastPath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    undoLastPath(view);
                }
            }
        );
        imageViewResetDraw.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 resetDraw(view);
             }
         }
        );
    }
    public void colorPickerSelectColor(View view){
        new ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true)  // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show();
    }

    private void setColor(ColorEnvelope envelope) {
        simplePaint.setColor(Color.valueOf(envelope.getColor()));
        imageViewColorPicker.setColorFilter(envelope.getColor());
    }
    public void undoLastPath(View view) {
        simplePaint.undoLastPath();
    }
    public void resetDraw(View view) {
        simplePaint.clearCanvas();
    }
}