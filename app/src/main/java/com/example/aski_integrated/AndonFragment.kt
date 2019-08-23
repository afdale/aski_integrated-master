package com.example.aski_integrated

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*


class AndonFragment : Fragment()  {

    var waktu: Long? = null
    var valuetanggal: Long? = null

    private var listAndon = ArrayList<AndonContainer>()
    lateinit var listAndonAdapter: BaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_andon, container, false)

        /* FirebaseMessaging.getInstance().subscribeToTopic("PE")
        FirebaseDatabase.getInstance().getReference().child("PE")*/


        try {

            FirebaseDatabase.getInstance().getReference().child("PE")
            FirebaseMessaging.getInstance().subscribeToTopic("PE")


            val problemListView = rootView.findViewById(R.id.alv) as ListView
            //problemListView.setAdapter((activity as AndonFragment).FavoritAdapter)

            listAndonAdapter = ListAndonAdapter(activity!!, listAndon)

            problemListView.adapter = listAndonAdapter
            problemListView.setOnItemClickListener { parent, view, position, id ->
                val launch3 = Intent(activity, BarcodeScannerActivity::class.java)
                launch3.putExtra("asal", "andon")
                launch3.putExtra("mold", listAndon[position].mMold)
                launch3.putExtra("problem", listAndon[position].mProblem)
                launch3.putExtra("key", listAndon[position].mKey)
                launch3.putExtra("timestamp", listAndon[position].mTimestamp)

                startActivity(launch3)
            }

        } catch (ex: Exception) {
            Log.i("ERRORMSG", "error : " + ex.toString())
        }

        /* //listAndon.add(AndonContainer(123123123,"asd","asd","asda","asdas","asdas","asdasd","asdasd"))

        //val listviewandon = rootView.findViewById(R.id.alv) as ListView
        //val listviewonprogress = rootView.findViewById(R.id.oplv) as ListView
        //val items = arrayOf("Item 1", "Item 2", "Item 3")

        //val adapter = ArrayAdapter(activity!!, R.layout.problem_adapter, items)
        //listviewandon.setAdapter(adapter)*/


        //override
        fun onResume() {
            super.onResume()
            try {
                FirebaseDatabase.getInstance().getReference().child("PE")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }
                        override fun onDataChange(p0: DataSnapshot) {
                            try {
                                listAndon.clear()
                                for (key in p0.children) {
                                    val t = key.child("timestamp").getValue(Long::class.java)

                                    val n = key.child("mold").getValue(String::class.java)
                                    val s = key.child("problem").getValue(String::class.java)
                                    val v = key.child("key").getValue(String::class.java)

                                    val t1 = key.child("techniciana").getValue(String::class.java)
                                    val t2 = key.child("technicianb").getValue(String::class.java)
                                    val t3 = key.child("technicianc").getValue(String::class.java)
                                    val t4 = key.child("techniciand").getValue(String::class.java)

                                    //val r = key.child("issuedby").getValue(String::class.java)

                                    listAndon.add(AndonContainer(t, n, s, v, t1, t2, t3, t4))

                                    Log.i("Datasnapshot", "Datasnapshot : " + key.toString())
                                    Log.i("kata s ", s.toString())
                                    Log.i("kata t ", t.toString())
                                    Log.i("kata n ", n)
                                }
                                listAndonAdapter.notifyDataSetChanged()

                            } catch (ex: Exception) {
                                Log.i("ERRORMSG", "error : " + ex.toString())
                            }
                        }
                    })
            } catch (ez: Exception) {
                Log.i("ERRORMSG","error : "+ez.toString())

            }
        }
        return rootView
    }


    inner class ListAndonAdapter : BaseAdapter {

        private var listProblemAndon = ArrayList<AndonContainer>()
        private var context: Context? = null

        constructor(context: Context, listProblemAndon: ArrayList<AndonContainer>) : super() {
            this.listProblemAndon = listProblemAndon
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            /*           val nomoldrp: TextView
                       val analisaTV: TextView*/
            //val view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)
            //val vh = ViewHolder(view)
            try {
                val view: View
                val vh: ViewHolder

                if (convertView == null) {
                    val inflater = LayoutInflater.from(getContext())
                    view = layoutInflater.inflate(R.layout.problem_adapter, parent, false)

                    //convertView = View.inflate (context,R.layout.list_content_layout, null);

                    val  nomoldrp = view.findViewById<TextView>(R.id.nomcdpTV) as TextView
                    val  analisaTV = view.findViewById<TextView>(R.id.problemTV) as TextView

                    val myObject = getItem(position)

                    //nomoldrp.setText(myObject.getName());

                    //nomoldrp = itemView.findViewById<TextView>(R.id.nomoldrp) as TextView

                    vh = ViewHolder(view)
                    view.tag = vh
                    Log.i("PC", "set Tag for ViewHolder, position: " + position)
                } else {
                    view = convertView
                    vh = view.tag as ViewHolder
                }
                //if (listProblemAndon.isNotEmpty()){
                //    himbauanTV.setVisibility(View.GONE)
                //  }

                vh.nomoldrp.text = listProblemAndon[position].mMold
                vh.analisaTV.text = listProblemAndon[position].mProblem
                valuetanggal = listProblemAndon[position].mTimestamp

                val selisihgmt: Long = System.currentTimeMillis() - SystemClock.elapsedRealtime()
                waktu = valuetanggal!! - selisihgmt


                Log.i("time", "waktu : " + waktu!!.toString())
                Log.i("time", "system millis : " + System.currentTimeMillis().toString())
                Log.i("time", "system clock : " + SystemClock.elapsedRealtime().toString())
                vh.hitung.base = waktu!!
                vh.hitung.start()

            } catch (ex: Exception) {
                Toast.makeText(
                    activity!!.applicationContext,
                    "$ex",
                    Toast.LENGTH_SHORT
                ).show()
            }

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


    private class ViewHolder(view: View?) {
        val nomoldrp: TextView
        val analisaTV: TextView
        val hitung: Chronometer

        init {
            this.nomoldrp = view?.findViewById<TextView>(R.id.nomcdpTV) as TextView
            this.analisaTV = view.findViewById<TextView>(R.id.problemTV) as TextView
            this.hitung = view.findViewById<Chronometer>(R.id.chronometer) as Chronometer
        }
    }

}