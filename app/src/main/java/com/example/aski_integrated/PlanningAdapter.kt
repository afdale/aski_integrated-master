<<<<<<< HEAD
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

class PlanningAdapter: AppCompatActivity() {

    lateinit var submit: ImageButton
    private var listOnProgress = ArrayList<PlanningContainer>()
    private lateinit var listOnprogressAdapter: BaseAdapter

    var totalestimasi:Long? = null
    var waktu:Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onprogress_submit)

        submit = findViewById<ImageButton>(R.id.submit)
        submit.setOnClickListener {
            val intent = Intent(this, PlanningSubmit::class.java)
            startActivity(intent)
        }
        try {
            val onprogressListView = findViewById<ListView>(R.id.onpro)
            listOnprogressAdapter = ListOnprogressAdapter(this, listOnProgress)

            onprogressListView.adapter = listOnprogressAdapter
            onprogressListView.setOnItemClickListener { parent, view, position, id ->
                val launch4 = Intent(this, ConfirmationPL::class.java)
                launch4.putExtra("asal", "onprogress")
                launch4.putExtra("mold", listOnProgress[position].mnomold)
                launch4.putExtra("tech1", listOnProgress[position].mtech1)
                launch4.putExtra("tech2", listOnProgress[position].mtech2)
                launch4.putExtra("tech3", listOnProgress[position].mtech3)
                launch4.putExtra("tech4", listOnProgress[position].mtech4)
                launch4.putExtra("problem", listOnProgress[position].mProblemETpl)
                launch4.putExtra("jenisproblem", listOnProgress[position].mjenisProblemETpl)
                launch4.putExtra("start", listOnProgress[position].mstart)
                launch4.putExtra("estimasi", listOnProgress[position].estimasipl)
                launch4.putExtra("key", listOnProgress[position].mKeypl)
                startActivity(launch4)
            }
        }catch (ex:Exception){
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            FirebaseDatabase.getInstance().getReference().child("planning").child("onprogress")
                .child("REPAIRING")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        listOnProgress.clear()
                        try {
                            for (key in p0.children) {
                                val a = key.child("mold").getValue(String::class.java)
                                val b = key.child("tech1").getValue(String::class.java)
                                val c = key.child("tech2").getValue(String::class.java)
                                val d = key.child("tech3").getValue(String::class.java)
                                val e = key.child("tech4").getValue(String::class.java)
                                val f = key.child("analisa").getValue(String::class.java)
                                val g = key.child("problem").getValue(String::class.java)
                                val h = key.child("jenisproblem").getValue(String::class.java)
                                val i = key.child("estimasi").getValue(Long::class.java)
                                val j = key.child("start").getValue(Long::class.java)
                                val k = key.child("key").getValue(String::class.java)


//constructor( mnomold :String?, tech1: String?, tech2: String?, tech3: String?, tech4: String?,analisa: String?,problem: String?, jenisproblem: String?, estimasi: Long?,start: Long?, key:String?)
                                listOnProgress.add(PlanningContainer(a,b,c,d,e,f,g,h,i,j,k))

                                Log.i("Datasnapshot", "Datasnapshot : " + key.toString())
                                Log.i("kata f ", f.toString())
                                Log.i("kata k ", k.toString())
                                Log.i("kata a ", a)
                            }
                            listOnprogressAdapter.notifyDataSetChanged()

                        } catch (ex: Exception) {
                            Log.i("ERRORMSG", "error : " + ex.toString())
                        }
                    }

                })
        } catch (ez:Exception){
            Toast.makeText(this, "$ez", Toast.LENGTH_LONG).show()
        }
    }


    inner class ListOnprogressAdapter : BaseAdapter {

        private var listProblemOnprogress = ArrayList<PlanningContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemOnprogress: ArrayList<PlanningContainer>) : super() {
            this.listProblemOnprogress = listProblemOnprogress
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.analisa_adapter, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("PC", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }


            vh.nomoldrpTV.text = listProblemOnprogress[position].mnomold
            vh.problemTV.text = listProblemOnprogress[position].mProblemETpl
            //vh.estimasiadapter.text = listProblemOnprogress[position].estimasipl

            totalestimasi = listProblemOnprogress[position].mstart
            //est = listProblemOnprogress[position].estimasipl

            val selisihgmt:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            waktu = totalestimasi!! - selisihgmt


            Log.i("time","waktu : "+waktu!!.toString())
            Log.i("time","system millis : "+System.currentTimeMillis().toString())
            Log.i("time","system clock : "+ SystemClock.elapsedRealtime().toString())
            vh.hitungrp.base = waktu!!
            vh.hitungrp.start()

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

    private class ViewHolder(view: View?) {
        val nomoldrpTV: TextView
        val problemTV: TextView
        val hitungrp: Chronometer
        //val estimasiadapter : TextView

        init {
            this.nomoldrpTV = view?.findViewById<TextView>(R.id.nomoldrpTV) as TextView
            this.problemTV = view.findViewById<TextView>(R.id.problemTV) as TextView
            this.hitungrp = view.findViewById<Chronometer>(R.id.hitungrp) as Chronometer
            //this.estimasiadapter = view.findViewById<TextView>(R.id.estimasiadapter) as TextView
        }
    }

}
=======
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

class PlanningAdapter: AppCompatActivity() {

    lateinit var submit: ImageButton
    private var listOnProgress = ArrayList<PlanningContainer>()
    private lateinit var listOnprogressAdapter: BaseAdapter

    var totalestimasi:Long? = null
    var waktu:Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onprogress_submit)

        submit = findViewById<ImageButton>(R.id.submit)
        submit.setOnClickListener {
            val intent = Intent(this, PlanningSubmit::class.java)
            startActivity(intent)
        }
        try {
            val onprogressListView = findViewById<ListView>(R.id.onpro)
            listOnprogressAdapter = ListOnprogressAdapter(this, listOnProgress)

            onprogressListView.adapter = listOnprogressAdapter
            onprogressListView.setOnItemClickListener { parent, view, position, id ->
                val launch4 = Intent(this, ConfirmationPL::class.java)
                launch4.putExtra("asal", "onprogress")
                launch4.putExtra("mold", listOnProgress[position].mnomold)
                launch4.putExtra("tech1", listOnProgress[position].mtech1)
                launch4.putExtra("tech2", listOnProgress[position].mtech2)
                launch4.putExtra("tech3", listOnProgress[position].mtech3)
                launch4.putExtra("tech4", listOnProgress[position].mtech4)
                launch4.putExtra("problem", listOnProgress[position].mProblemETpl)
                launch4.putExtra("jenisproblem", listOnProgress[position].mjenisProblemETpl)
                launch4.putExtra("start", listOnProgress[position].mstart)
                launch4.putExtra("estimasi", listOnProgress[position].estimasipl)
                launch4.putExtra("key", listOnProgress[position].mKeypl)
                startActivity(launch4)
            }
        }catch (ex:Exception){
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            FirebaseDatabase.getInstance().getReference().child("planning").child("onprogress")
                .child("REPAIRING")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        listOnProgress.clear()
                        try {
                            for (key in p0.children) {
                                val a = key.child("mold").getValue(String::class.java)
                                val b = key.child("tech1").getValue(String::class.java)
                                val c = key.child("tech2").getValue(String::class.java)
                                val d = key.child("tech3").getValue(String::class.java)
                                val e = key.child("tech4").getValue(String::class.java)
                                val f = key.child("analisa").getValue(String::class.java)
                                val g = key.child("problem").getValue(String::class.java)
                                val h = key.child("jenisproblem").getValue(String::class.java)
                                val i = key.child("estimasi").getValue(Long::class.java)
                                val j = key.child("start").getValue(Long::class.java)
                                val k = key.child("key").getValue(String::class.java)


//constructor( mnomold :String?, tech1: String?, tech2: String?, tech3: String?, tech4: String?,analisa: String?,problem: String?, jenisproblem: String?, estimasi: Long?,start: Long?, key:String?)
                                listOnProgress.add(PlanningContainer(a,b,c,d,e,f,g,h,i,j,k))

                                Log.i("Datasnapshot", "Datasnapshot : " + key.toString())
                                Log.i("kata f ", f.toString())
                                Log.i("kata k ", k.toString())
                                Log.i("kata a ", a)
                            }
                            listOnprogressAdapter.notifyDataSetChanged()

                        } catch (ex: Exception) {
                            Log.i("ERRORMSG", "error : " + ex.toString())
                        }
                    }

                })
        } catch (ez:Exception){
            Toast.makeText(this, "$ez", Toast.LENGTH_LONG).show()
        }
    }


    inner class ListOnprogressAdapter : BaseAdapter {

        private var listProblemOnprogress = ArrayList<PlanningContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemOnprogress: ArrayList<PlanningContainer>) : super() {
            this.listProblemOnprogress = listProblemOnprogress
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.analisa_adapter, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("PC", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }


            vh.nomoldrpTV.text = listProblemOnprogress[position].mnomold
            vh.problemTV.text = listProblemOnprogress[position].mProblemETpl
            //vh.estimasiadapter.text = listProblemOnprogress[position].estimasipl

            totalestimasi = listProblemOnprogress[position].mstart
            //est = listProblemOnprogress[position].estimasipl

            val selisihgmt:Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
            waktu = totalestimasi!! - selisihgmt


            Log.i("time","waktu : "+waktu!!.toString())
            Log.i("time","system millis : "+System.currentTimeMillis().toString())
            Log.i("time","system clock : "+ SystemClock.elapsedRealtime().toString())
            vh.hitungrp.base = waktu!!
            vh.hitungrp.start()

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

    private class ViewHolder(view: View?) {
        val nomoldrpTV: TextView
        val problemTV: TextView
        val hitungrp: Chronometer
        //val estimasiadapter : TextView

        init {
            this.nomoldrpTV = view?.findViewById<TextView>(R.id.nomoldrpTV) as TextView
            this.problemTV = view.findViewById<TextView>(R.id.problemTV) as TextView
            this.hitungrp = view.findViewById<Chronometer>(R.id.hitungrp) as Chronometer
            //this.estimasiadapter = view.findViewById<TextView>(R.id.estimasiadapter) as TextView
        }
    }

}
>>>>>>> ff9e8ff41cf574c5585b922b7e7156a9c134b26c
