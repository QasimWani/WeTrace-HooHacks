package com.example.quarantine.fragments.symptoms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager

import com.example.quarantine.R
import com.example.quarantine.adapters.SymptomsAdapters
import com.example.quarantine.models.symptoms.SymptomsItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreQuestionnaire : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }
    private var arrayList:ArrayList<SymptomsItem> ?= null
    private var gridView: GridView ? = null
    private var symptomsAdapters:SymptomsAdapters ? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_symptoms_1, container, false)
        gridView = root.findViewById(R.id.grid_symptoms_pre_questionnaire)
        arrayList = ArrayList()
        arrayList = setDataList()
        symptomsAdapters = activity?.applicationContext?.let { SymptomsAdapters(it, arrayList!!) }
        gridView?.adapter = symptomsAdapters
        gridView?.onItemClickListener = this
        return root
    }

    private fun setDataList() : ArrayList<SymptomsItem>{
        var arrayList:ArrayList<SymptomsItem> = ArrayList();
        arrayList.add(SymptomsItem(R.drawable.positive, "Tested Positive with COVID-19", "1"))
        arrayList.add(SymptomsItem(R.drawable.negative, "Tested Negative with COVID-19", "2"))
        arrayList.add(SymptomsItem(R.drawable.not_tested, "Not been tested yet", "3"))
        arrayList.add(SymptomsItem(R.drawable.question, "Waiting for test results", "4"))
        return arrayList
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: Int) =
            PreQuestionnaire().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var items:SymptomsItem = arrayList!!.get(position)
        if (items.metadata != "1")
        {
            Toast.makeText(activity?.applicationContext, items.metadata, Toast.LENGTH_SHORT).show()
            activity?.findViewById<GridView>(R.id.grid_symptoms_pre_questionnaire)?.id?.let {

                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    it, MainQuestions())?.addToBackStack(null)?.commit()
            }

        }
        else
        {
            Toast.makeText(activity?.applicationContext, "You have CORONA!!!", Toast.LENGTH_SHORT).show()
        }

    }
}
