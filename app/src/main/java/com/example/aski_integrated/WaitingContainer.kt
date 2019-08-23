package com.example.aski_integrated

class WaitingContainer {

    var mMold: String? = null
    var mProblem: String? = null
    var mTimestamp: Long? = null
    var mRepairTimestamp: Long? = null
    var mKey:String? = null
    var mPic:String? = null
    var mPerbaikan:String? = null
    var mJenisproblem:String? = null
    var mFinishRepairTimestamp: Long? = null

    constructor() {

    }

    constructor(finishrepairtimestamp: Long?,timestamp: Long?,
                repairtimestamp: Long?, mold: String?, problem: String?,
                key: String?,jenisproblem: String?,perbaikan: String?,pic: String?)
    {
        this.mTimestamp = timestamp
        this.mRepairTimestamp = repairtimestamp
        this.mMold = mold
        this.mProblem = problem
        this.mKey = key
        this.mFinishRepairTimestamp = finishrepairtimestamp
        this.mPic = pic
        this.mPerbaikan = perbaikan
        this.mJenisproblem = jenisproblem
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

    fun getFinishRepairTimestamp(): Long? {
        return mFinishRepairTimestamp
    }

    fun setFinishRepairTimestamp(finishrepairtimestamp: Long?) {
        mFinishRepairTimestamp = finishrepairtimestamp
    }

    fun getJenisproblem(): String? {
        return mJenisproblem
    }

    fun setJenisproblem(jenisproblem: String?) {
        mJenisproblem = jenisproblem
    }

    fun getPerbaikan(): String? {
        return mPerbaikan
    }

    fun setPerbaikan(perbaikan: String?) {
        mPerbaikan = perbaikan
    }

    fun getPic(): String? {
        return mPic
    }

    fun setPi(pic: String?) {
        mPic = pic
    }



}