package com.example.aski_integrated

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.formsubmit.*

class ImprovementSubmit : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    private var btnmold: ImageButton? = null
    private var btntech1: ImageButton? = null
    private var btntech2: ImageButton? = null
    private var btntech3: ImageButton? = null
    private var btntech4: ImageButton? = null
    private var btnfinish: ImageButton? = null

    lateinit var nomold: TextView
    lateinit var technician1: TextView
    lateinit var technician2: TextView
    lateinit var technician3: TextView
    lateinit var technician4: TextView
    //lateinit var estimasi: TextView
    //var estimasiwkt: String = "Estimasi Waktu"

    lateinit var problem: EditText
    lateinit var analisa: EditText
    lateinit var jenisproblem: EditText
    lateinit var estimasi_jam: EditText
    lateinit var estimasi_menit: EditText

    lateinit var asal: String
    private lateinit var kunci: String

    var mold: String? = "N/A"
    var tech1: String? = "N/A"
    var tech2: String? = "N/A"
    var tech3: String? = "N/A"
    var tech4: String? = "N/A"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formsubmit)
        //val database = FirebaseDatabase.getInstance()
        ref = FirebaseDatabase.getInstance().getReference().child("improvement").child("onprogress")
            .child("REPAIRING")

        btnfinish = findViewById<ImageButton>(R.id.finishProgressBTNrp)
        btnmold = findViewById<ImageButton>(R.id.scanmoldrp)
        btntech1 = findViewById<ImageButton>(R.id.scantech1)
        btntech2 = findViewById<ImageButton>(R.id.scantech2)
        btntech3 = findViewById<ImageButton>(R.id.scantech3)
        btntech4 = findViewById<ImageButton>(R.id.scantech4)
        nomold = findViewById<TextView>(R.id.nomoldrpTV)
        technician1 = findViewById<TextView>(R.id.tech1rp)
        technician2 = findViewById<TextView>(R.id.tech2rp)
        technician3 = findViewById<TextView>(R.id.tech3rp)
        technician4 = findViewById<TextView>(R.id.tech4rp)
        //estimasi = findViewById<TextView>(R.id.estimasiTV)

        problem = findViewById<EditText>(R.id.problemETrp)
        analisa = findViewById<EditText>(R.id.analisarp)
        jenisproblem = findViewById<EditText>(R.id.jenisProblemETrp)
        estimasi_jam = findViewById<EditText>(R.id.estimasi_jamET)
        estimasi_menit = findViewById<EditText>(R.id.estimasi_menitET)

        nomold.text = mold
        technician1.text = tech1
        technician2.text = tech2
        technician3.text = tech3
        technician4.text = tech4

/*
        val estimasiBTN = findViewById<ImageButton>(R.id.estimasiBTN)
        val estimasiTV = findViewById<TextView>(R.id.estimasiTV)

        estimasiBTN.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                estimasiTV.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }*/

        btnmold!!.setOnClickListener {
            val intent = Intent(this@ImprovementSubmit, BarcodeScannerActivity::class.java)
            intent.putExtra("asal", "mold")
            startActivityForResult(intent, 1)
        }
        btntech1!!.setOnClickListener {
            val intent = Intent(this@ImprovementSubmit, BarcodeScannerActivity::class.java)
            intent.putExtra("asal", "ID1")
            startActivityForResult(intent, 2)
        }
        btntech2!!.setOnClickListener {
            val intent = Intent(this@ImprovementSubmit, BarcodeScannerActivity::class.java)
            intent.putExtra("asal", "ID2")
            startActivityForResult(intent, 3)
        }
        btntech3!!.setOnClickListener {
            val intent = Intent(this@ImprovementSubmit, BarcodeScannerActivity::class.java)
            intent.putExtra("asal", "ID3")
            startActivityForResult(intent, 4)
        }
        btntech4!!.setOnClickListener {
            val intent = Intent(this@ImprovementSubmit, BarcodeScannerActivity::class.java)
            intent.putExtra("asal", "ID4")
            startActivityForResult(intent, 5)
        }
        btnfinish!!.setOnClickListener {
            savedata()

            val intent = Intent(this, ImprovementAdapter::class.java)
         finish()
        }
    }

    private fun savedata() {
        try {
            val mold = nomoldrpTV.text.toString()
            val tech1 = tech1rp.text.toString()
            val tech2 = tech2rp.text.toString()
            val tech3 = tech3rp.text.toString()
            val tech4 = tech4rp.text.toString()
            val analisa = analisarp.text.toString()
            val problem = problemETrp.text.toString()
            val estimasi = estimasi.text.toString()
            val jenisproblem = jenisProblemETrp.text.toString()
            val estimasijam = estimasi_jamET.text.toString()
            val estimasimenit = estimasi_menitET.text.toString()

            //val hasil = Mold(mold, tech1, tech2, tech3, tech4, problem, analisa, jenisproblem, estimasi, key, start)
            val moldId = ref.push().key.toString()

            var estimasi_jam: Long = 0
            var estimasi_menit: Long = 0
            var totalestimasi: Long = 0

            estimasi_jam = estimasi_jamET.text.toString().toLong()
            estimasi_menit = estimasi_menitET.text.toString().toLong()

            totalestimasi = estimasi_jam + estimasi_menit

            Toast.makeText(this, "$totalestimasi", Toast.LENGTH_LONG).show()


            ref.child(moldId).child("key").setValue(moldId)
            ref.child(moldId).child("mold").setValue(mold)
            ref.child(moldId).child("tech1").setValue(tech1)
            ref.child(moldId).child("tech2").setValue(tech2)
            ref.child(moldId).child("tech3").setValue(tech3)
            ref.child(moldId).child("tech4").setValue(tech4)
            ref.child(moldId).child("analisa").setValue(analisa)
            ref.child(moldId).child("problem").setValue(problem)
            ref.child(moldId).child("jenisproblem").setValue(jenisproblem)
            ref.child(moldId).child("estimasi").setValue(totalestimasi)
            ref.child(moldId).child("start").setValue(ServerValue.TIMESTAMP)
            ref.child(moldId).child("estimasijam").setValue(estimasi_jam)
            ref.child(moldId).child("estimasimenit").setValue(estimasi_menit)

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

            nomoldrpTV.setText("")
            tech1rp.setText("")
            tech2rp.setText("")
            tech3rp.setText("")
            tech4rp.setText("")
            problemETrp.setText("")
            estimasi_jamET.setText("")
            estimasi_menitET.setText("")
            analisarp.setText("")
            jenisProblemETrp.setText("")


        } catch (ex: Exception) {
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        super.onResume()
        nomold.text = mold
        technician1.text = tech1
        technician2.text = tech2
        technician3.text = tech3
        technician4.text = tech4
    }

    fun mold(): String? {
        return mold
    }

    fun technician1(): String? {
        return tech1
    }

    fun technician2(): String? {
        return tech2
    }

    fun technician3(): String? {
        return tech3
    }

    fun technician4(): String? {
        return tech4
    }

    fun DoUpload() {
        val launch4 = Intent(this, BarcodeScannerActivity::class.java)
        launch4.putExtra("asal", asal)
        //launch4.putExtra("mc2",myMC)
        try {
            startActivity(launch4)
        } catch (ex: Exception) {
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }
    }

    fun DoResult(code: Int) {
        val launch4 = Intent(this, BarcodeScannerActivity::class.java)
        launch4.putExtra("asal", asal)
        try {
            startActivityForResult(launch4, code)
        } catch (ex: Exception) {
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                mold = data!!.getStringExtra("mold")
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                tech1 = data!!.getStringExtra("tech1")
            }
        }
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                tech2 = data!!.getStringExtra("tech2")
            }
        }
        if (requestCode == 4) {
            if (resultCode == Activity.RESULT_OK) {
                tech3 = data!!.getStringExtra("tech3")
            }
        }
        if (requestCode == 5) {
            if (resultCode == Activity.RESULT_OK) {
                tech4 = data!!.getStringExtra("tech4")
            }
        }
    }

    fun goCancelProgress(view: View) {
        finish()
    }

    fun goFinishProgress(view: View) {


        finish()
    }
}