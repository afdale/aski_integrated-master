package com.example.aski_integrated

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class ActivityAndon : AppCompatActivity() {

    var waktu:Long? = null
    var valuetanggal:Long? = null

    lateinit var kunci:String

    //lateinit var himbauanTV:TextView
    private var listAndon = ArrayList<AndonContainer>()
    lateinit var listAndonAdapter: BaseAdapter

    private var listOnProgress = ArrayList<OnProgressContainer>()
    lateinit var listOnprogressAdapter: BaseAdapter

    private var listWaiting = ArrayList<WaitingContainer>()
    lateinit var listWaitingAdapter: BaseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_andon)


        try {
            //himbauanTV = findViewById<TextView>(R.id.himbauanTV)
            val problemListView = findViewById<ListView>(R.id.alv)
            val onprogressListView = findViewById<ListView>(R.id.oplv)
            val waitingListView = findViewById<ListView>(R.id.wclv)

            listAndonAdapter = ListAndonAdapter(this, listAndon)
            listOnprogressAdapter = ListOnprogressAdapter(this, listOnProgress)
            listWaitingAdapter = ListWaitingAdapter(this, listWaiting)

            problemListView.adapter = listAndonAdapter
            problemListView.setOnItemClickListener { parent, view, position, id ->
                val launch3 = Intent(this, BarcodeAndon::class.java)
                launch3.putExtra("asal", "andon")
                launch3.putExtra("mc", listAndon[position].mMc)
                launch3.putExtra("problem", listAndon[position].mProblem)
                launch3.putExtra("key", listAndon[position].mKey)
                launch3.putExtra("start", listAndon[position].mTimestamp)
                launch3.putExtra("issuedby", listAndon[position].mIssuedby)
                startActivity(launch3)
            }

            onprogressListView.adapter = listOnprogressAdapter
            onprogressListView.setOnItemClickListener { parent, view, position, id ->
                val launch4 = Intent(this, OnProgressActivity::class.java)
                launch4.putExtra("asal", "onprogress")
                launch4.putExtra("mc", listOnProgress[position].mMc)
                launch4.putExtra("problem", listOnProgress[position].mProblem)
                launch4.putExtra("key", listOnProgress[position].mKey)
                launch4.putExtra("start", listOnProgress[position].mTimestamp)
                launch4.putExtra("start_repair", listOnProgress[position].mRepairTimestamp)
                launch4.putExtra("issuedby", listOnProgress[position].mIssuedby)

                startActivity(launch4)
            }

            waitingListView.adapter = listWaitingAdapter
            waitingListView.setOnItemClickListener { parent, view, position, id ->
                val launch3 = Intent(this, BarcodeAndon::class.java)
                launch3.putExtra("asal", "waiting")
                launch3.putExtra("mc", listWaiting[position].mMc)
                launch3.putExtra("problem", listWaiting[position].mProblem)
                launch3.putExtra("key", listWaiting[position].mKey)
                launch3.putExtra("start", listWaiting[position].mTimestamp)
                launch3.putExtra("issuedby", listWaiting[position].mIssuedby)
                launch3.putExtra("start_repair", listWaiting[position].mRepairTimestamp)
                launch3.putExtra("finish_repair", listWaiting[position].mFinishRepairTimestamp)
                launch3.putExtra("pic", listWaiting[position].mPic)
                launch3.putExtra("jenisproblem", listWaiting[position].mJenisproblem)
                launch3.putExtra("perbaikan", listWaiting[position].mPerbaikan)
                startActivity(launch3)
            }
            //if (listAndon.isEmpty()){
            //  himbauanTV.setVisibility(View.VISIBLE)
            //}
        }catch (ex:Exception){
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        try {
            FirebaseDatabase.getInstance().getReference().child("andon").child("PE")
                .addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {

                        try{
                            listAndon.clear()
                            for(key in p0.children) {
                                val n = key.child("mc").getValue(String::class.java)
                                val s = key.child("problem").getValue(String::class.java)
                                val v =key.child("key").getValue(String::class.java)
                                val t = key.child("start").getValue(Long::class.java)
                                val r = key.child("issuedby").getValue(String::class.java)

                                listAndon.add(AndonContainer(t,n,s,v,r))

                                Log.i("Datasnapshot","Datasnapshot : "+key.toString())
                                Log.i("kata s ",s.toString())
                                Log.i("kata t ",t.toString())
                                Log.i("kata n ",n)
                            }
                            listAndonAdapter.notifyDataSetChanged()

                        }catch (ex:Exception){
                            Log.i("ERRORMSG","error : "+ex.toString())
                        }
                    }

                })

            FirebaseDatabase.getInstance().getReference().child("andon").child("onprogress").child("PE")
                .addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        listOnProgress.clear()
                        try{
                            for(key in p0.children) {
                                val n = key.child("mc").getValue(String::class.java)
                                val s = key.child("problem").getValue(String::class.java)
                                val v =key.child("key").getValue(String::class.java)
                                val t = key.child("start").getValue(Long::class.java)
                                val rt = key.child("start_repair").getValue(Long::class.java)
                                val r = key.child("issuedby").getValue(String::class.java)

                                listOnProgress.add(OnProgressContainer(t,rt,n,s,v,r))

                                Log.i("Datasnapshot","Datasnapshot : "+key.toString())
                                Log.i("kata s ",s.toString())
                                Log.i("kata t ",t.toString())
                                Log.i("kata n ",n)
                            }
                            listOnprogressAdapter.notifyDataSetChanged()

                        }catch (ex:Exception){
                            Log.i("ERRORMSG","error : "+ex.toString())
                        }
                    }

                })

            FirebaseDatabase.getInstance().getReference().child("andon").child("waiting").child("PE")
                .addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        listWaiting.clear()
                        try{
                            for(key in p0.children) {
                                val n = key.child("mc").getValue(String::class.java)
                                val s = key.child("problem").getValue(String::class.java)
                                val v =key.child("key").getValue(String::class.java)
                                val r = key.child("issuedby").getValue(String::class.java)
                                val jp = key.child("jenisproblem").getValue(String::class.java)
                                val p = key.child("perbaikan").getValue(String::class.java)
                                val pic = key.child("pic").getValue(String::class.java)
                                val t = key.child("start").getValue(Long::class.java)
                                val rt = key.child("start_repair").getValue(Long::class.java)
                                val frt = key.child("finish_repair").getValue(Long::class.java)

                                listWaiting.add(WaitingContainer(frt,t,rt,n,s,v,r,jp,p,pic))

                                Log.i("Datasnapshot","Datasnapshot : "+key.toString())
                                Log.i("kata s ",s.toString())
                                Log.i("kata t ",t.toString())
                                Log.i("kata n ",n)
                            }
                            listWaitingAdapter.notifyDataSetChanged()

                        }catch (ex:Exception){
                            Log.i("ERRORMSG","error : "+ex.toString())
                        }
                    }

                })
        }catch (ez:Exception){
            Toast.makeText(this, "$ez", Toast.LENGTH_LONG).show()
        }


        //if (listAndon.isEmpty()){
        //  himbauanTV.setVisibility(View.VISIBLE)
        //}
    }


    inner class ListAndonAdapter : BaseAdapter {

        private var listProblemAndon = ArrayList<AndonContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemAndon: ArrayList<AndonContainer>): super(){
            this.listProblemAndon = listProblemAndon
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            //val view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
            //val vh = ViewHolder(view)

            val view: View
            val vh:ViewHolder

            if(convertView == null){
                view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("PC","set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            //if (listProblemAndon.isNotEmpty()){
            //    himbauanTV.setVisibility(View.GONE)
            //  }

            vh.mcTV.text = listProblemAndon[position].mMc
            vh.problemTV.text = listProblemAndon[position].mProblem

            valuetanggal = listProblemAndon[position].mTimestamp

            val selisihgmt:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            waktu = valuetanggal!! - selisihgmt


            Log.i("time","waktu : "+waktu!!.toString())
            Log.i("time","system millis : "+System.currentTimeMillis().toString())
            Log.i("time","system clock : "+ SystemClock.elapsedRealtime().toString())
            vh.hitung.base = waktu!!
            vh.hitung.start()

            return view
        }

        override fun getItem(position: Int): Any {

            return listProblemAndon[position]
        }

        override fun getItemId(position: Int): Long {

            return position.toLong()
        }

        override fun getCount(): Int {

            return listProblemAndon.size
        }

    }

    inner class ListOnprogressAdapter : BaseAdapter {

        private var listProblemOnprogress = ArrayList<OnProgressContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemOnprogress: ArrayList<OnProgressContainer>): super(){
            this.listProblemOnprogress = listProblemOnprogress
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            //val view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
            //val vh = ViewHolder(view)

            val view: View
            val vh:ViewHolder

            if(convertView == null){
                view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("PC","set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            //if (listProblemAndon.isNotEmpty()){
            //    himbauanTV.setVisibility(View.GONE)
            //  }

            vh.mcTV.text = listProblemOnprogress[position].mMc
            vh.problemTV.text = listProblemOnprogress[position].mProblem

            valuetanggal = listProblemOnprogress[position].mRepairTimestamp

            val selisihgmt:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            waktu = valuetanggal!! - selisihgmt


            Log.i("time","waktu : "+waktu!!.toString())
            Log.i("time","system millis : "+System.currentTimeMillis().toString())
            Log.i("time","system clock : "+ SystemClock.elapsedRealtime().toString())
            vh.hitung.base = waktu!!
            vh.hitung.start()

            return view
        }

        override fun getItem(position: Int): Any {

            return listProblemOnprogress[position]
        }

        override fun getItemId(position: Int): Long {

            return position.toLong()
        }

        override fun getCount(): Int {

            return listProblemOnprogress.size
        }

    }

    inner class ListWaitingAdapter : BaseAdapter {

        private var listProblemWaiting = ArrayList<WaitingContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemWaiting: ArrayList<WaitingContainer>): super(){
            this.listProblemWaiting = listProblemWaiting
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            //val view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
            //val vh = ViewHolder(view)

            val view: View
            val vh:ViewHolder

            if(convertView == null){
                view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("PC","set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            //if (listProblemAndon.isNotEmpty()){
            //    himbauanTV.setVisibility(View.GONE)
            //  }

            vh.mcTV.text = listProblemWaiting[position].mMc
            vh.problemTV.text = listProblemWaiting[position].mProblem

            valuetanggal = listProblemWaiting[position].mFinishRepairTimestamp

            val selisihgmt:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            waktu = valuetanggal!! - selisihgmt

            Log.i("time","waktu : "+waktu!!.toString())
            Log.i("time","system millis : "+System.currentTimeMillis().toString())
            Log.i("time","system clock : "+ SystemClock.elapsedRealtime().toString())
            vh.hitung.base = waktu!!
            vh.hitung.start()

            return view
        }

        override fun getItem(position: Int): Any {
            return listProblemWaiting[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listProblemWaiting.size
        }
    }


    private class ViewHolder(view: View?){
        val mcTV: TextView
        val problemTV: TextView
        val hitung: Chronometer


        init {
            this.mcTV = view?.findViewById<TextView>(R.id.noMCTV) as TextView
            this.problemTV = view.findViewById<TextView>(R.id.problemTV) as TextView
            this.hitung = view.findViewById<Chronometer>(R.id.hitung) as Chronometer
        }
    }
}
