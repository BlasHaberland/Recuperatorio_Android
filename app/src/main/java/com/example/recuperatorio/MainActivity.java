package com.example.recuperatorio;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.recuperatorio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // observables
        viewModel.getMOperacionEuroADolar().observe(this, valor -> {
            binding.etEuros.setEnabled(false); // Alternativa: !valor
            binding.etDolares.setEnabled(true); // Alternativa: valor
            binding.etDolares.setText("");
            binding.tvConversion.setText("");
        });

        viewModel.getMOperacionDolarAEuro().observe(this, valor -> {
            binding.etDolares.setEnabled(false);
            binding.etEuros.setEnabled(true);
            binding.etEuros.setText("");
            binding.tvConversion.setText("");
        });

        viewModel.getMResultadoConversion().observe(this, resultado ->{
            binding.tvConversion.setText(String.valueOf(resultado));
            binding.tvError.setText("");
        });

        viewModel.getMMensajeError().observe(this, mensaje -> {
            binding.tvError.setText(mensaje);
        });

        binding.rgConversion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                viewModel.setMTipoConversion(checkedId);
            }
        });

        binding.btnConvertir.setOnClickListener(v -> {
            String ValorDolares = binding.etDolares.getText().toString();
            String ValorEuros = binding.etEuros.getText().toString();

            viewModel.convertir(ValorDolares, ValorEuros);
        });
    }
}