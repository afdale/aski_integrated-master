package com.example.aski_integrated


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment


class RepairingFragment : Fragment() {

    lateinit var v: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            v = inflater.inflate(R.layout.fragment_repairing, container, false)

            val breakdown = v.findViewById<ImageButton>(R.id.breakdown) as ImageButton
            breakdown.setOnClickListener {
                val intent = Intent(activity, BreakdownAdapter::class.java)
                activity!!.startActivity(intent)
            }

            /*val planning = v.findViewById<ImageButton>(R.id.planning) as ImageButton
            planning.setOnClickListener {
                val intent = Intent(activity, PlanningAdapter::class.java)
                activity!!.startActivity(intent)
            }*/
/*
            val improvement = v.findViewById<ImageButton>(R.id.improvement) as ImageButton
            improvement.setOnClickListener {
                val intent = Intent(activity, ImprovementAdapter::class.java)
                activity!!.startActivity(intent)
            }*/

            return v

    }
}


