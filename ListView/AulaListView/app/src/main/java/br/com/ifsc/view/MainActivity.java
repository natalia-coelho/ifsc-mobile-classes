package br.com.ifsc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String frutas[] = new String[]{"pera", "uva", "maça", "mamão"};
    public static ArrayList<String> fruta = new ArrayList<>();
    ListView listView;
    EditText editText;
    FrutaController frutaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frutaController = new FrutaController();
        listView=findViewById(R.id.listView);
        editText=findViewById(R.id.edText);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fruta.add(editText.getText().toString());
                atualizarLista();
            }
        });
        atualizarLista();

    }

    public  void atualizarLista(){
        listView.setAdapter(
                new FrutaAdapter(
                        getApplicationContext(),
                        R.layout.item_lista,
                        frutaController.FRUTAS
                )
        );
    }
}