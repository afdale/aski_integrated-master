package com.example.aski_integrated

class OnProgressContainer {


    var mMc: String? = null
    var mProblem: String? = null
    var mTimestamp: Long? = null
    var mRepairTimestamp: Long? = null
    var mKey:String? = null
    var mIssuedby:String? = null

    constructor() {

    }

    constructor(timestamp: Long?,repairtimestamp: Long?, mc: String?, problem: String?, key: String?,issuedby: String?) {
        this.mTimestamp = timestamp
        this.mRepairTimestamp = repairtimestamp
        this.mMc = mc
        this.mProblem = problem
        this.mKey = key
        this.mIssuedby = issuedby
    }

    fun getIssuedby(): String? {
        return mIssuedby
    }

    fun setIssuedby(issuedby: String?) {
        mIssuedby = issuedby
    }

    fun getKey(): String? {
        return mKey
    }

    fun setKey(key: String?) {
        mKey = key
    }

    fun getMc(): String? {
        return mMc
    }

    fun setMc(mc: String?) {
        mMc = mc
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