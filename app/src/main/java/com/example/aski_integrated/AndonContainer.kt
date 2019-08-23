package com.example.aski_integrated

class AndonContainer {


    var mMold: String? = null
    var mProblem: String? = null
    var mTimestamp: Long? = null
    var mKey:String? = null
    var mTechnicianRepaira:String? = null
    var mTechnicianRepairb:String? = null
    var mTechnicianRepairc:String? = null
    var mTechnicianRepaird:String? = null

    constructor() {
    }

    constructor(timestamp: Long?, mold: String?, problem: String?,
                key: String?,techniciana: String?, technicianb: String?, technicianc: String?, techniciand: String?) {
        this.mTimestamp = timestamp
        this.mMold = mold
        this.mProblem = problem
        this.mKey = key
        this.mTechnicianRepaira = techniciana
        this.mTechnicianRepairb = technicianb
        this.mTechnicianRepairc = technicianc
        this.mTechnicianRepaird = techniciand
    }

    fun getTechnicianRepaira(): String? {
        return mTechnicianRepaira
    }

    fun setTechnicianRepaira(technicianrepaira: String?) {
        mTechnicianRepaira = technicianrepaira
    }

    fun getTechnicianRepairb(): String? {
        return mTechnicianRepairb
    }

    fun setTechnicianRepairb(technicianrepairb: String?) {
        mTechnicianRepairb = technicianrepairb
    }
    fun getTechnicianRepairc(): String? {
        return mTechnicianRepairc
    }

    fun setTechnicianRepairc(technicianrepairc: String?) {
        mTechnicianRepairc = technicianrepairc
    }

    fun getTechnicianRepaird(): String? {
        return mTechnicianRepaird
    }

    fun setTechnicianRepaird(technicianrepaird: String?) {
        mTechnicianRepaird = technicianrepaird
    }


    fun getKey(): String? {
        return mKey
    }

    fun setKey(key: String?) {
        mKey = key
    }


    fun getmold(): String? {
        return mMold
    }

    fun setmold(mold: String?) {
        mMold = mold
    }

    fun getProblem(): String? {
        return mProblem
    }

    fun setProblem(problem: String?) {
        mProblem = problem
    }

    fun getTimestamp(): Long? {
        return mTimestamp
    }

    fun setTimestamp(timestamp: Long?) {
        mTimestamp = timestamp
    }



}