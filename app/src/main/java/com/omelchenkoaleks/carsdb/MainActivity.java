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

//        // С ПОМОЩЬЮ ЭТОГО ОБЪЕКТА ДОБАВЛЯЕМ В БАЗУ ДАННЫЕ
//        dataBaseHandler.addCar(new Car("Toyota", "30 000 $"));
//        dataBaseHandler.addCar(new Car("Opel", "25 000 $"));
//        dataBaseHandler.addCar(new Car("Mercedes", "50 000 $"));
//        dataBaseHandler.addCar(new Car("KIA", "28 000 $"));
//
//        // СОЗДАЕМ ПУСТОЙ СПИСОК, В КОТОРЫЙ ИЗВЛЕКАЕМ ВСЕ ЗАПИСИ ИЗ db
//        List<Car> carList = dataBaseHandler.getAllCars();
//
//        // ВЫВОДИМ В ЖУРНАЛ ВСЕ ЧТО НАХОДИТСЯ В ЭТОМ ЛИСТЕ
//        for (Car car : carList) {
//            Log.d("Info", "ID: " + car.getId()
//                    + " NAME: " + car.getName() + " PRICE: " + car.getPrice());
//        }

        Car car = dataBaseHandler.getCar(20);
        Log.d("Info:", "ID " + car.getId() + ", NAME " + car.getName() + ", PRICE " + car.getPrice());
    }
}
