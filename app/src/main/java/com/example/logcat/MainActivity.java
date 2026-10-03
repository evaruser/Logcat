package com.example.logcat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText etA, etB;
    private Button btnDividir;
    private TextView tvResultado;

    private static final String TAG = "CESBA_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        btnDividir = findViewById(R.id.btnDividir);
        tvResultado = findViewById(R.id.tvResultado);

        Log.i(TAG, "App iniciada correctamente (onCreate)");

        btnDividir.setOnClickListener(v -> {
            Log.d(TAG, "Botoón presionado");

            String aTxt = etA.getText().toString().trim();
            String bTxt = etB.getText().toString().trim();

            Log.d(TAG, "Entrada A" + aTxt);
            Log.d(TAG, "Entrada B" + bTxt);

            if (aTxt.isEmpty() || bTxt.isEmpty()){
                Log.w(TAG, "Campos vacíos");
                Toast.makeText(this, "Completa ambos campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int a = Integer.parseInt(aTxt);
                int b = Integer.parseInt(bTxt);

                Log.i(TAG, "Convertidos: a=" + a + ", b=" + b);
                int resultado = a / b;
                tvResultado.setText("Resultado: " + resultado);
                Log.i(TAG, "Resultado mostrado" + resultado);

            } catch (ArithmeticException e){
                Log.e(TAG, "División entre cero: " + e.getMessage());
                Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e){
                Log.e(TAG, "Formato inválido" + e.getMessage());
                Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.e(TAG, "Error inesperado" + e.getMessage());
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}