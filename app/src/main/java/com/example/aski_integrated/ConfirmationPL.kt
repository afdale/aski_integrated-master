
package com.example.aski_integrated

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class ConfirmationPL : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var connectionClass: ConnectionClass

    private var asal: String? = null
    private var nomold: String? = null
    private var tech1: String? = null
    private var tech2: String? = null
    private var tech3: String? = null
    private var tech4: String? = null

    private var analisa: String? = null
    private var problem: String? = null
    private var jenisproblem: String? = null
    private var estimasi: String? = null
    private var start: String? = null
    private var perbaikan: String? = null
    private lateinit var kunci: String
    private var valueprogress: Int= 0


    lateinit var finishProgressBTNrp: ImageButton
    private lateinit var radiogroup: RadioGroup
    private lateinit var btnBongkar: RadioButton
    private lateinit var btnRepair: RadioButton
    private lateinit var btnAssembly: RadioButton
    private lateinit var btnTesting: RadioButton
    private lateinit var btnFinish: RadioButton
    private lateinit var btnUpdate: ImageButton
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
        perbaikan = getIntent().getStringExtra("perbaikan")
        start = getIntent().getStringExtra("start")

        nomoldconf.text = nomold
        tech1conf.text = tech1
        tech2conf.text = tech2
        tech3conf.text = tech3
        tech4conf.text = tech4
        analisaconf.text = analisa
        problemconf.text = problem
        jenisproblemconf.text = jenisproblem
        estconf.text = estimasi



        radiogroup  = findViewById<RadioGroup>(R.id.radiogroupconf)
        btnBongkar  = findViewById<RadioButton>(R.id.btnBongkar)
        btnRepair  = findViewById<RadioButton>(R.id.btnRepair)
        btnAssembly  = findViewById<RadioButton>(R.id.btnAssembly)
        btnTesting  = findViewById<RadioButton>(R.id.btnTesting)
        btnFinish  = findViewById<RadioButton>(R.id.btnFinish)
        btnUpdate  = findViewById<ImageButton>(R.id.btnUpdate)
        ref = FirebaseDatabase.getInstance().getReference().child("breakdown").child("onprogress")
            .child("REPAIRING")

        btnUpdate!!.setOnClickListener {

            //Toast.makeText(this, "${radiogroup.checkedRadioButtonId}", Toast.LENGTH_LONG).show()
            if (radiogroup.checkedRadioButtonId==2131296352){
                valueprogress = 20
            }
            else if (radiogroup.checkedRadioButtonId==2131296354){
                valueprogress = 40
            }
            else if (radiogroup.checkedRadioButtonId==2131296351){
                valueprogress = 60
            }
            else if (radiogroup.checkedRadioButtonId==2131296355){
                valueprogress = 80
            }
            else if (radiogroup.checkedRadioButtonId==2131296353){
                valueprogress = 100
            }
            savedata()
            Toast.makeText(this, "${valueprogress}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun savedata() {
        try {


            ref.child(kunci).child("valueprogress").setValue(valueprogress)

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()


        } catch (ex: Exception) {
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }

    }


    fun goCancelProgress(view: View){
        finish()
    }

    fun goFinishProgress(view: View){
       perbaikan = perbaikanETconf.text.toString()

        Douploadreport(this).execute()
        finish()
    }

    inner class Douploadreport(var activity: ConfirmationPL) : AsyncTask<String, String, String>() {
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
                    val query = "INSERT INTO planning (mold,tech1,tech2,tech3,tech4,analisa, problem,jenisproblem,estimasi,start,perbaikan) VALUES ('$nomold','$tech1','$tech2','$tech3','$tech4','$analisa','$problem','$jenisproblem','$estimasi','$start','$perbaikan')"
                    //sql server String query = "insert into planing (mold,tech1,tech2,tech3,tech4,analisa, problem,jenisproblem,estimasi,start,perbaikan)
                    // values ('" + mold + "','" + tech1 + "','" + tech2 + "','" + tech3 + "','" + tech4+ "','" + analisa + "','" + problem + "','" + jenisproblem + "','" + estimasi + "','" + start + "','" + perbaikan + "')";
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
            Toast.makeText(this@ConfirmationPL, "" + z, Toast.LENGTH_LONG).show()
            if(isSuccess)
            {
                FirebaseDatabase.getInstance().getReference().child("planning").child("onprogress")
                    .child("REPAIRING").child(kunci).removeValue()
                this@ConfirmationPL.finish()
            }
        }
    }
}