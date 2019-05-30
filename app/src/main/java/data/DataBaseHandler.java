package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import model.Car;
import utils.Util;

/*
    Handler (управлять) класс будет управляющим базой данных
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }


    /* -------------------- CRUD --------------------- */

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        // этот объект нужен, чтобы взаимодействовать с db
        // что-то вроде HashMap() - ключ-значение
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        // теперь вставляем нашу запись в db
        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Car getCar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // объект этого класса позволит перемещаться по db
        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE,},
                Util.KEY_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            // метод возвращает true, если объект cursor содержит записи
            cursor.moveToFirst();
        }

        /*
            создается объект, который будем возвращать - в него извлекается вся
            информация из нашего cursor
         */
        Car car = new Car(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return car;
    }

    /*
        этот метод будет возвращать список объектов
        используем List<> потому-что это интерфейс от который имплементируют другие способы
        записи в список (ArrayList и т.п) - Если мы захоим в будущем использовать любой из них
        нам не нужно будет переделывать код ... )))
     */
    public List<Car> getAllCars() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Car> carsList = new ArrayList<>();

        /*
            создаем строку в которую будет помещен SQL код
            SELECT выбираем * означает все FROM откуда
         */
        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;

        // теперь нужне объект класса Cursor, который позволит пермещатся по db и выбирать нужные данные
        Cursor cursor = db.rawQuery(selectAllCars, null);
        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));

                carsList.add(car);

            } while (cursor.moveToNext()); // что значит пока есть следующий элемент в cursor
        }

        return carsList;
    }

    /*
        при обновлении записи возвращается id этой записи, поэтому тип метода int

        эта запись Util.KEY_ID + "=?" указывает куда мы будем помещать информацию (по id)
     */
    public int updateCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        // при вызове метода возвращается id записи, которая обновляется
        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[] {String.valueOf(car.getId())});
    }
}
