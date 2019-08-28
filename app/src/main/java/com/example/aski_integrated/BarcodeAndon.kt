package com.example.aski_integrated

import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.lang.Exception

class BarcodeAndon : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null
    lateinit var tulisanTVNEW: TextView
    lateinit var msgTV: TextView

    lateinit var connectionClass: ConnectionClass

    private lateinit var asal:String
    private lateinit var mc:String
    private lateinit var problem:String
    private lateinit var kunci:String
    private lateinit var issuedby:String
    private var perbaikan:String? = null
    private var jenisproblem:String? = null
    private var pic:String? = null
    private var start :Long? = null
    private var start_repair :Long? = null
    private var finish_repair :Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)
        mScannerView = findViewById<ZXingScannerView>(R.id.scanner)

        connectionClass = ConnectionClass()

        tulisanTVNEW = findViewById<TextView>(R.id.tulisanTVNEW)
        msgTV = findViewById<TextView>(R.id.msgTV)

        asal = getIntent().getStringExtra("asal")
        mc = getIntent().getStringExtra("mc")
        problem = getIntent().getStringExtra("problem")
        kunci = getIntent().getStringExtra("key")

        issuedby = getIntent().getStringExtra("issuedby")
        start = getIntent().getLongExtra("start",0)
        start_repair = getIntent().getLongExtra("start_repair",0)

        finish_repair = getIntent().getLongExtra("finish_repair",0)
        pic = getIntent().getStringExtra("pic")
        perbaikan = getIntent().getStringExtra("perbaikan")
        jenisproblem = getIntent().getStringExtra("jenisproblem")

        if (asal=="andon"){
            tulisanTVNEW.text = "Scan QR CODE pada MC $mc untuk membatalkan panggilan."
            //val tulisan1 = "Scan QR CODE pada MC $mc untuk membatalkan panggilan."
        }
        if (asal=="onprogress"){
            tulisanTVNEW.text = "Scan QR CODE pada TAGID anda untuk menyelesaikan."
        }
        if (asal=="waiting"){
            tulisanTVNEW.text = "Scan QR CODE pada TAGID $issuedby untuk menyelesaikan."
        }

    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun onStop() {
        finish()
        super.onStop()
    }


    override fun handleResult(rawResult: Result) {
        Log.v("TAG", rawResult.getText()) // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString())


        //Tulis task disini
        //var dialog = Dialog(this, android.R.style.Theme_Translucent_NoTitleBar)
        //var z = ""
        if ((asal =="scan1")&&rawResult.getText().contains("MC",false))
        {
            val launch4 = Intent(this, BarcodeAndon::class.java)
            launch4.putExtra("asal","MCON1")
            launch4.putExtra("mc",rawResult.getText())
            startActivity(launch4)
        }

        if ((rawResult.getText() == mc)&&asal=="andon")
        {
            //launch4.putExtra("mpid", firebaseBarcode.displayValue)
            var z = ""
            var dialog = Dialog(this,android.R.style.Theme_Translucent_NoTitleBar)
            val view = this.layoutInflater.inflate(R.layout.progress,null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()

            FirebaseDatabase.getInstance().getReference().child("andonswitch").setValue(1)
            FirebaseDatabase.getInstance().getReference().child("buzzer").setValue(1)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("key").setValue(kunci)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("start_repair").setValue(ServerValue.TIMESTAMP)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("start").setValue(start)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("mc").setValue(mc)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("problem").setValue(problem)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).child("issuedby").setValue(issuedby)
            FirebaseDatabase.getInstance().getReference().child("andon").child("PE").child(kunci).removeValue()
                .addOnSuccessListener {
                    z = "Terima kasih, Anda telah menjawab panggilan di MC $mc."
                }.addOnFailureListener {
                    z = "Error $it"
                }.addOnCompleteListener {
                    dialog.dismiss()
                    Toast.makeText(this, "$z", Toast.LENGTH_LONG).show()
                    finish()
                }
            /*launch4.putExtra("mc", mc)
            launch4.putExtra("problem", problem)
            launch4.putExtra("key", kunci)
            launch4.putExtra("timestamp", tanggal)
            startActivity(launch4)*/

        }
        if (asal=="onprogress"&&rawResult.getText().contains("ID",false))
        {
            val dataidcard = rawResult.getText().substringAfter("FN:").substringBefore("\n")
            //launch4.putExtra("mpid", firebaseBarcode.displayValue)
            var z = ""
            var dialog = Dialog(this,android.R.style.Theme_Translucent_NoTitleBar)
            val view = this.layoutInflater.inflate(R.layout.progress,null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()

            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("key").setValue(kunci)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("finish_repair").setValue(ServerValue.TIMESTAMP)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("start_repair").setValue(start_repair)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("start").setValue(start)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("mc").setValue(mc)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("problem").setValue(problem)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("perbaikan").setValue(perbaikan)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("jenisproblem").setValue(jenisproblem)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("issuedby").setValue(issuedby)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).child("pic").setValue(dataidcard)
            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE").child(kunci).removeValue()
                .addOnSuccessListener {
                    z = "Terima kasih, Anda telah menyelesaikan panggilan di MC $mc. Harap menunggu konfirmasi dari $issuedby."
                }.addOnFailureListener {
                    z = "Error $it"
                }.addOnCompleteListener {
                    dialog.dismiss()
                    Toast.makeText(this, "$z", Toast.LENGTH_LONG).show()
                    finish()
                }
            /*launch4.putExtra("mc", mc)
            launch4.putExtra("problem", problem)
            launch4.putExtra("key", kunci)
            launch4.putExtra("timestamp", tanggal)
            startActivity(launch4)*/
        }
        if (asal=="waiting"&&rawResult.getText().contains("ID",false)&&rawResult.getText().substringAfter("FN:").substringBefore("\n") == issuedby)
        {


            Douploadreport(this).execute()

            /*
            //launch4.putExtra("mpid", firebaseBarcode.displayValue)

            var z = ""
            var dialog = Dialog(this,android.R.style.Theme_Translucent_NoTitleBar)
            val view = this.layoutInflater.inflate(R.layout.progress,null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()


            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("key").setValue(kunci)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("finish_repair").setValue(ServerValue.TIMESTAMP)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("start_repair").setValue(start_repair)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("start").setValue(start)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("mc").setValue(mc)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("problem").setValue(problem)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("perbaikan").setValue(perbaikan)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("jenisproblem").setValue(jenisproblem)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("issuedby").setValue(issuedby)
            FirebaseDatabase.getInstance().getReference().child("andon").child("closed").child("Moldshop").child(kunci).child("pic").setValue(pic)
            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("Moldshop").child(kunci).removeValue()
                .addOnSuccessListener {
                    z = "Terima kasih, Problem pada MC $mc telah selesai."
                }.addOnFailureListener {
                    z = "Error $it"
                }.addOnCompleteListener {
                    dialog.dismiss()
                    Toast.makeText(this, "$z", Toast.LENGTH_LONG).show()
                    finish()
                }
            /*launch4.putExtra("mc", mc)
            launch4.putExtra("problem", problem)
            launch4.putExtra("key", kunci)
            launch4.putExtra("timestamp", tanggal)
            startActivity(launch4)*/
        */}
        //qrList.add(QrCode("Generic", firebaseBarcode.displayValue))
        else{
            msgTV.text = "QR CODE yang anda scan salah, Silahkan Scan QR CODE pada Mesin."
            mScannerView!!.resumeCameraPreview(this)
        }

    }

    inner class Douploadreport(var activity: BarcodeAndon) : AsyncTask<String, String, String>() {

        var dialog = Dialog(activity,android.R.style.Theme_Translucent_NoTitleBar)

        private var z = ""
        internal var isSuccess = false

        override fun onPreExecute() {
            val view = activity.layoutInflater.inflate(R.layout.progress,null)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()
            //setDialog(true)
            //progressDialog.setMessage("Loading...")
            //progressDialog.show()
        }

        override fun doInBackground(vararg params: String): String {
            try {
                val con = connectionClass.CONN()
                if (con == null) {
                    z = "Please check your internet connection"
                } else {
                    val linesaatini = mc.substringAfter("LINE ").substringBefore(" STATION")
                    val station = mc.substringAfterLast("STATION ")
                    //val query = "INSERT INTO checksheetpainting(kunci,date,shift,timestamp,mp_id,line,cat_expired,rawpart_visual,loading_hangerdansubjig,waping_solvent,waping_lap,settingroom1_temp,airblow1_airpressure,matic_undercoat_balancingangin,matic_undercoat_settinggun_atomize,matic_undercoat_settinggun_pattern,matic_undercoat_settinggun_paint,settingroom2_temp,matic_topcoat_balancingangin,matic_topcoat_settinggun_atomize,matic_topcoat_settinggun_pattern,matic_topcoat_settinggun_paint,settingroom3_temp,bakingoven_temp) VALUES ($key,$datetimedata,$shift,$timestamp,'$mpid','$line','$maticcat','$maticrawpart','$matichanger','$maticwapingsolvent','$maticwapinglap',$maticsettingtemp1,$maticairpress1,'$bamatic1','$maticsettingatomize1','$maticsettingpattern1','$maticsettingpaint1',$maticsettingtemp2,'$bamatic2','$maticsettingatomize2','$maticsettingpattern2','$maticsettingpaint2',$maticsettingtemp3,$maticoventemp)"
                    val query2 = "INSERT INTO andon(start_andon,start_repair,finish_repair,closed_by_op,dept,no_mc,line,station,problem,jenis_problem,perbaikan,issued_by,pic) VALUES ($start,$start_repair,$finish_repair,${System.currentTimeMillis()},'PE','$mc','$linesaatini','$station','$problem','$jenisproblem','$perbaikan','$issuedby','$pic')"
                    val query = "INSERT INTO pe(start_andon,start_repair,finish_repair,closed_by_op,dept,no_mc,problem,jenis_problem,perbaikan,issued_by,pic) VALUES ($start,$start_repair,$finish_repair,${System.currentTimeMillis()},'PE','$mc','$problem','$jenisproblem','$perbaikan','$issuedby','$pic')"
                    val stmt = con.createStatement()
                    stmt.executeUpdate(query)
                    stmt.executeUpdate(query2)

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
            //setDialog(false)

            dialog.dismiss()
            Toast.makeText(this@BarcodeAndon, "" + z, Toast.LENGTH_LONG).show()

            if(isSuccess)
            {
                FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE").child(kunci).removeValue()
                this@BarcodeAndon.finish()
            }
            //progressDialog.hide()
        }
    }
}
