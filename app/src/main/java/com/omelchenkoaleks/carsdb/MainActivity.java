package com.omelchenkoaleks.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import data.DataBaseHandler;
import model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // СОЗДАЕМ ОБЪЕКТ, КОТОРЫЙ УПРАВЛЯЕТ БАЗОЙ ДАННЫХ
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        Log.i("happy", String.valueOf(dataBaseHandler.getCarsCount()));

        // СОЗДАЕМ ПУСТОЙ СПИСОК, В КОТОРЫЙ ИЗВЛЕКАЕМ ВСЕ ЗАПИСИ ИЗ db
        List<Car> carList = dataBaseHandler.getAllCars();

        // ВЫВОДИМ В ЖУРНАЛ ВСЕ ЧТО НАХОДИТСЯ В ЭТОМ ЛИСТЕ
        for (Car car : carList) {
            Log.d("happy", "ID: " + car.getId()
                    + " NAME: " + car.getName() + " PRICE: " + car.getPrice());
        }

        Car deletingCar = dataBaseHandler.getCar(2);
        dataBaseHandler.deleteCar(deletingCar);

        // ВЫВОДИМ В ЖУРНАЛ ВСЕ ЧТО НАХОДИТСЯ В ЭТОМ ЛИСТЕ
        for (Car car : carList) {
            Log.d("happy", "ID: " + car.getId()
                    + " NAME: " + car.getName() + " PRICE: " + car.getPrice());
        }

//        Log.e("happy", "ID " + deletingCar.getId() + ", NAME " + deletingCar.getName() + ", PRICE " + deletingCar.getPrice());
    }
}
