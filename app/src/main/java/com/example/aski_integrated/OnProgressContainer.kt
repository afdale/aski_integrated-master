package com.example.aski_integrated


class OnProgressContainer {

    var mMold: String? = null
    var mProblem: String? = null
    var mTimestamp: Long? = null
    var mRepairTimestamp: Long? = null
    var mKey:String? = null
    var mTechnicianRepaira:String? = null
    var mTechnicianRepairb:String? = null
    var mTechnicianRepairc:String? = null
    var mTechnicianRepaird:String? = null



    constructor(timestamp: Long?,repairtimestamp: Long?, mold: String?,
                problem: String?, key: String?,techniciana: String?, technicianb: String?,
                technicianc: String?, techniciand: String?) {
        this.mTimestamp = timestamp
        this.mRepairTimestamp = repairtimestamp
        this.mMold = mold
        this.mProblem = problem
        this.mKey = key
        this.mTechnicianRepaira = techniciana
        this.mTechnicianRepairb = technicianb
        this.mTechnicianRepairc = technicianc
        this.mTechnicianRepaird = techniciand
    }



    fun getKey(): String? {
        return mKey
    }

    fun setKey(key: String?) {
        mKey = key
    }


    fun getMold(): String? {
        return mMold
    }

    fun setMold(mold: String?) {
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

    fun getRepairTimestamp(): Long? {
        return mRepairTimestamp
    }

    fun setRepairTimestamp(repairtimestamp: Long?) {
        mRepairTimestamp = repairtimestamp
    }



}