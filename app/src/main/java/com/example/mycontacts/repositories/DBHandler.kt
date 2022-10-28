import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mycontacts.models.MyContacts
import dagger.hilt.android.qualifiers.ApplicationContext


class DBHandler(@ApplicationContext context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + NUMBER + " TEXT,"
                + EMAIL + " TEXT,"
                + IMAGE + " TEXT)")


        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewContact(contacts: MyContacts) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME, contacts.name)
        values.put(NUMBER, contacts.phone)
        values.put(EMAIL, contacts.email)
        values.put(IMAGE, contacts.photo)

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {

        private const val DB_NAME = "contactsDB"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "myContacts"

        // below variable is for our id column.
        private const val ID = "id"

        // below variable is for our course name column
        private const val NAME = "name"

        // below variable id for our course duration column.
        private const val NUMBER = "number"

        // below variable for our course description column.
        private const val EMAIL = "email"

        // below variable is for our course tracks column.
        private const val IMAGE = "imageUri"
    }
}