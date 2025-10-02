package com.example.recuperatorio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Conversion conversion;
    private MutableLiveData<Boolean> mOperacionDolarAEuro;
    private MutableLiveData<Boolean> mOperacionEuroADolar;
    private MutableLiveData<Double> mResultadoConversion;
    private MutableLiveData<String> mMensajeError;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Boolean> getMOperacionDolarAEuro() {
        if (mOperacionDolarAEuro == null) {
            mOperacionDolarAEuro = new MutableLiveData<>();
        }
        return mOperacionDolarAEuro;
    }

    public LiveData<Boolean> getMOperacionEuroADolar() {
        if (mOperacionEuroADolar == null) {
            mOperacionEuroADolar = new MutableLiveData<>();
        }
        return mOperacionEuroADolar;
    }

    public LiveData<Double> getMResultadoConversion() {
        if (mResultadoConversion == null) {
            mResultadoConversion = new MutableLiveData<>();
        }
        return mResultadoConversion;
    }

    public LiveData<String> getMMensajeError() {
        if (mMensajeError == null) {
            mMensajeError = new MutableLiveData<>();
        }
        return mMensajeError;
    }

    public void setMTipoConversion(int idRadioButton) {
        if (idRadioButton == R.id.rbDolaresAEuros) {
            conversion = Conversion.DOLAR_A_EURO;
            mOperacionDolarAEuro.setValue(true);
            mOperacionEuroADolar.setValue(false);
        } else {
            conversion =conversion.EURO_A_DOLAR;
            mOperacionEuroADolar.setValue(true);
            mOperacionDolarAEuro.setValue(false);
        }
    }

    public void convertir(String valorDolares, String valorEuros) {
        try {
            if (conversion == Conversion.DOLAR_A_EURO) {
                double valor = Double.parseDouble(valorDolares);
                mResultadoConversion.setValue(valor * conversion.getTasa());
            } else if (conversion == Conversion.EURO_A_DOLAR) {
                double valor = Double.parseDouble(valorEuros);
                mResultadoConversion.setValue(valor * conversion.getTasa());
            } else {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            mMensajeError.setValue("Ingrese un valor numérico válido.");
        } catch (Exception e) {
            mMensajeError.setValue("Seleccione un tipo de conversión");
        }
    }
}