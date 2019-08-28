<<<<<<< HEAD
package com.example.aski_integrated

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView

class OnProgressActivity : AppCompatActivity() {

    var wakturepairsaatini:Long? = null
    var timestamprepair:Long? = null

    private  var asal:String? = null
    private  var mc:String? = null
    private  var problem:String? = null
    private  var issuedby:String? = null
    private  var kunci:String? = null
    private var start :Long? = null
    private var start_repair :Long? = null

    private lateinit var noMCTV: TextView
    private lateinit var issuedPICTV: TextView
    private lateinit var waktuRepairCMeter: Chronometer
    private lateinit var problemET: EditText
    private lateinit var perbaikanET: EditText
    private lateinit var jenisProblemET: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formandon)

        noMCTV = findViewById<TextView>(R.id.noMCTV)
        issuedPICTV = findViewById<TextView>(R.id.issuedPICTV)
        waktuRepairCMeter = findViewById<Chronometer>(R.id.waktuRepairCMeter)
        problemET = findViewById<EditText>(R.id.problemET)
        perbaikanET = findViewById<EditText>(R.id.perbaikanET)
        jenisProblemET = findViewById<EditText>(R.id.jenisProblemET)

        asal = getIntent().getStringExtra("asal")
        issuedby = getIntent().getStringExtra("issuedby")
        mc = getIntent().getStringExtra("mc")
        problem = getIntent().getStringExtra("problem")
        kunci = getIntent().getStringExtra("key")
        start = getIntent().getLongExtra("start",0)
        start_repair = getIntent().getLongExtra("start_repair",0)

        timestamprepair = start_repair!!
        val selisihgmtrepair:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
        wakturepairsaatini = timestamprepair!! - selisihgmtrepair

        waktuRepairCMeter.base = wakturepairsaatini!!
        waktuRepairCMeter.start()

        noMCTV.text = mc
        problemET.setText(problem)
        issuedPICTV.text = issuedby

    }

    fun goCancelProgress(view: View){
        finish()
    }

    fun goFinishProgress(view: View){

        val launch4 = Intent(this, BarcodeAndon::class.java)
        launch4.putExtra("asal","onprogress")
        launch4.putExtra("mc", mc)
        launch4.putExtra("problem", problem)
        launch4.putExtra("key", kunci)
        launch4.putExtra("start", start)
        launch4.putExtra("start_repair", start_repair)
        launch4.putExtra("perbaikan", perbaikanET.text.toString())
        launch4.putExtra("issuedby", issuedby)
        launch4.putExtra("jenisproblem", jenisProblemET.text.toString())

        startActivity(launch4)
        finish()
    }
}
=======
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

    private  var asal:String? = null
    private  var mold:String? = null
    private  var problem:String? = null
    private  var techniciana:String? = null
    private  var technicianb:String? = null
    private  var technicianc:String? = null
    private  var techniciand:String? = null
    private  var kunci:String? = null
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

        noMold = findViewById<TextView>(R.id.nomcdpTV)
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
>>>>>>> ff9e8ff41cf574c5585b922b7e7156a9c134b26c
