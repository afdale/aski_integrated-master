/*
package com.example.aski_integrated


import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnProgressActivity: AppCompatActivity() {

    var wakturepairsaatini:Long? = null
    var timestamprepair:Long? = null

    private lateinit var asal:String
    private lateinit var mold:String
    private lateinit var problem:String
    private lateinit var techniciana:String
    private lateinit var technicianb:String
    private lateinit var technicianc:String
    private lateinit var techniciand:String
    private lateinit var kunci:String
    private var start :Long? = null
    private var start_repair :Long? = null

    private lateinit var noMold: TextView
    private lateinit var techa: TextView
    private lateinit var techb: TextView
    private lateinit var techc: TextView
    private lateinit var techd: TextView
    private lateinit var waktuRepairCMeter: Chronometer
    private lateinit var problemET: EditText
    private lateinit var perbaikanET: EditText
    private lateinit var jenisProblemET: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formandon)

        noMold = findViewById<TextView>(R.id.nomcrpTV)
        techa = findViewById<TextView>(R.id.techa)
        techb = findViewById<TextView>(R.id.techb)
        techc = findViewById<TextView>(R.id.techc)
        techd = findViewById<TextView>(R.id.techd)
        waktuRepairCMeter = findViewById<Chronometer>(R.id.waktuRepairCMeter)
        problemET = findViewById<EditText>(R.id.problemET)
        perbaikanET = findViewById<EditText>(R.id.PerbaikanET)
        jenisProblemET = findViewById<EditText>(R.id.jenisProblemET)

        asal = getIntent().getStringExtra("asal")
        techniciana = getIntent().getStringExtra("techa")
        technicianb = getIntent().getStringExtra("techb")
        technicianc = getIntent().getStringExtra("techc")
        techniciand = getIntent().getStringExtra("techd")
        mold = getIntent().getStringExtra("mold")
        problem = getIntent().getStringExtra("problem")
        kunci = getIntent().getStringExtra("key")
        start = getIntent().getLongExtra("start",0)
        start_repair = getIntent().getLongExtra("start_repair",0)

        timestamprepair = start_repair!!
        val selisihgmtrepair:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
        wakturepairsaatini = timestamprepair!! - selisihgmtrepair

        waktuRepairCMeter.base = wakturepairsaatini!!
        waktuRepairCMeter.start()

        noMold.text = mold
        problemET.setText(problem)
        techa.text = techniciana
        techb.text = technicianb
        techc.text = technicianc
        techd.text = techniciand

    }

    fun goCancelProgress(view: View){
        finish()
    }


}
*/
