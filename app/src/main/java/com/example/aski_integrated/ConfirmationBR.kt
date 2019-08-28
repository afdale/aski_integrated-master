<<<<<<< HEAD
package com.example.aski_integrated

import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class ConfirmationBR : AppCompatActivity() {

    lateinit var connectionClass: ConnectionClass

    private var asal:String?  = null
    private var nomold:String?  = null
    private  var tech1:String? = null
    private  var tech2:String? = null
    private  var tech3:String? = null
    private  var tech4:String? = null

    private  var analisa:String? = null
    private  var problem:String? = null
    private  var jenisproblem:String? = null
    private  var estimasi:String? = null
    private  var start:String? = null
    private var perbaikan:String? = null
    private lateinit var kunci:String

    lateinit var finishProgressBTNrp: ImageButton

    private lateinit var nomoldconf: TextView
    private lateinit var tech1conf: TextView
    private lateinit var tech2conf: TextView
    private lateinit var tech3conf: TextView
    private lateinit var tech4conf: TextView

    private lateinit var analisaconf: TextView
    private lateinit var problemconf: TextView

    private lateinit var jenisproblemconf: TextView
    private lateinit var estconf: TextView
    private lateinit var perbaikanETconf: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_confirmation)

        nomoldconf = findViewById<TextView>(R.id.nomoldconf)
        tech1conf = findViewById<TextView>(R.id.tech1conf)
        tech2conf = findViewById<TextView>(R.id.tech2conf)
        tech3conf = findViewById<TextView>(R.id.tech3conf)
        tech4conf = findViewById<TextView>(R.id.tech4conf)
        problemconf = findViewById<TextView>(R.id.problemconf)
        estconf = findViewById<TextView>(R.id.estconf)
        analisaconf = findViewById<TextView>(R.id.analisaconf)
        perbaikanETconf = findViewById<EditText>(R.id.perbaikanETconf)
        jenisproblemconf = findViewById<TextView>(R.id.jenisproblemconf)

        connectionClass = ConnectionClass()
        finishProgressBTNrp = findViewById<ImageButton>(R.id.finishProgressBTNrp)


        kunci = getIntent().getStringExtra("key")
        asal = getIntent().getStringExtra("asal")
        nomold = getIntent().getStringExtra("mold")
        tech1 = getIntent().getStringExtra("tech1")
        tech2 = getIntent().getStringExtra("tech2")
        tech3 = getIntent().getStringExtra("tech3")
        tech4 = getIntent().getStringExtra("tech4")
        analisa = getIntent().getStringExtra("analisa")
        problem = getIntent().getStringExtra("problem")
        jenisproblem = getIntent().getStringExtra("jenisproblem")
        estimasi = getIntent().getStringExtra("estimasi")
        start = getIntent().getStringExtra("start")

        nomoldconf.text = nomold
        tech1conf.text = tech1
        tech2conf.text = tech2
        tech3conf.text = tech3
        tech4conf.text = tech4

        analisaconf.text = analisa

        problemconf.text = problem
        jenisproblemconf.text = jenisproblem

        estconf.text = estimasi.toString()

    }

    fun goCancelProgress(view: View){
        finish()
    }

    fun goFinishProgress(view: View){

        perbaikan = perbaikanETconf.text.toString()
        Douploadreport(this).execute()
        finish()
    }

    inner class Douploadreport(var activity: ConfirmationBR) : AsyncTask<String, String, String>() {
        var dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)
        private var z = ""
        internal var isSuccess = false

        override fun onPreExecute() {
            val view = activity.layoutInflater.inflate(R.layout.progress, null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()

        }
        override fun doInBackground(vararg params: String): String {
            try {
                val con = connectionClass.CONN()

                if (con == null) {
                    z = "Please check your internet connection"
                } else {
                    val query = "INSERT INTO breakdown (mold,tech1,tech2,tech3,tech4,analisa, problem,jenisproblem,estimasi,start,perbaikan) VALUES ('$nomold','$tech1','$tech2','$tech3','$tech4','$analisa','$problem','$jenisproblem','$estimasi','$start','$perbaikan')"
                    val stmt = con.createStatement()
                    stmt.executeUpdate(query)
                    z = "Upload successfull"
                    isSuccess = true
                }
            } catch (ex: Exception) {
                isSuccess = false
                z = "ERROR : $ex"
            }
            return z
        }

        override fun onPostExecute(s: String) {
            dialog.dismiss()
            Toast.makeText(this@ConfirmationBR, "" + z, Toast.LENGTH_LONG).show()

            if(isSuccess)
            {
                FirebaseDatabase.getInstance().getReference().child("breakdown").child("onprogress")
                    .child("REPAIRING").removeValue()
                this@ConfirmationBR.finish()
            }
        }
    }
=======
package com.example.aski_integrated

import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ConfirmationBR : AppCompatActivity() {

    lateinit var connectionClass: ConnectionClass

    private var asal:String?  = null
    private var nomold:String?  = null
    private  var tech1:String? = null
    private  var tech2:String? = null
    private  var tech3:String? = null
    private  var tech4:String? = null

    private  var analisa:String? = null
    private  var problem:String? = null
    private  var jenisproblem:String? = null
    private  var estimasi:String? = null
    private  var start:String? = null
    private var perbaikan:String? = null
    private lateinit var kunci:String

    lateinit var finishProgressBTNrp: ImageButton

    private lateinit var nomoldconf: TextView
    private lateinit var tech1conf: TextView
    private lateinit var tech2conf: TextView
    private lateinit var tech3conf: TextView
    private lateinit var tech4conf: TextView

    private lateinit var analisaconf: TextView
    private lateinit var problemconf: TextView

    private lateinit var jenisproblemconf: TextView
    private lateinit var estconf: TextView
    private lateinit var perbaikanETconf: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_confirmation)

        nomoldconf = findViewById<TextView>(R.id.nomoldconf)
        tech1conf = findViewById<TextView>(R.id.tech1conf)
        tech2conf = findViewById<TextView>(R.id.tech2conf)
        tech3conf = findViewById<TextView>(R.id.tech3conf)
        tech4conf = findViewById<TextView>(R.id.tech4conf)
        problemconf = findViewById<TextView>(R.id.problemconf)
        estconf = findViewById<TextView>(R.id.estconf)
        analisaconf = findViewById<TextView>(R.id.analisaconf)
        perbaikanETconf = findViewById<EditText>(R.id.perbaikanETconf)
        jenisproblemconf = findViewById<TextView>(R.id.jenisproblemconf)

        connectionClass = ConnectionClass()
        finishProgressBTNrp = findViewById<ImageButton>(R.id.finishProgressBTNrp)


        kunci = getIntent().getStringExtra("key")
        asal = getIntent().getStringExtra("asal")
        nomold = getIntent().getStringExtra("mold")
        tech1 = getIntent().getStringExtra("tech1")
        tech2 = getIntent().getStringExtra("tech2")
        tech3 = getIntent().getStringExtra("tech3")
        tech4 = getIntent().getStringExtra("tech4")
        analisa = getIntent().getStringExtra("analisa")
        problem = getIntent().getStringExtra("problem")
        jenisproblem = getIntent().getStringExtra("jenisproblem")
        estimasi = getIntent().getStringExtra("estimasi")
        start = getIntent().getStringExtra("start")

        nomoldconf.text = nomold
        tech1conf.text = tech1
        tech2conf.text = tech2
        tech3conf.text = tech3
        tech4conf.text = tech4

        analisaconf.text = analisa

        problemconf.text = problem
        jenisproblemconf.text = jenisproblem

        estconf.text = estimasi.toString()

    }

    fun goCancelProgress(view: View){
        finish()
    }

    fun goFinishProgress(view: View){

        perbaikan = perbaikanETconf.text.toString()
        Douploadreport(this).execute()
        finish()
    }

    inner class Douploadreport(var activity: ConfirmationBR) : AsyncTask<String, String, String>() {
        var dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)
        private var z = ""
        internal var isSuccess = false

        override fun onPreExecute() {
            val view = activity.layoutInflater.inflate(R.layout.progress, null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()

        }
        override fun doInBackground(vararg params: String): String {
            try {
                val con = connectionClass.CONN()

                if (con == null) {
                    z = "Please check your internet connection"
                } else {
                    val query = "INSERT INTO breakdown (mold,tech1,tech2,tech3,tech4,analisa, problem,jenisproblem,estimasi,start,perbaikan) VALUES ('$nomold','$tech1','$tech2','$tech3','$tech4','$analisa','$problem','$jenisproblem','$estimasi','$start','$perbaikan')"
                    val stmt = con.createStatement()
                    stmt.executeUpdate(query)

                }

            } catch (ex: Exception) {


            }
            return z
        }

        override fun onPostExecute(s: String) {
            dialog.dismiss()
            Toast.makeText(this@ConfirmationBR, "" + z, Toast.LENGTH_LONG).show()

            if(isSuccess)
            {
                FirebaseDatabase.getInstance().getReference().child("breakdown").child("onprogress")
                    .child("REPAIRING").child(kunci).removeValue()
                this@ConfirmationBR.finish()
            }
        }
    }
>>>>>>> ff9e8ff41cf574c5585b922b7e7156a9c134b26c
}