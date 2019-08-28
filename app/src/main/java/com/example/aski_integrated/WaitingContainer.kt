package com.example.aski_integrated

class WaitingContainer {
<<<<<<< HEAD

=======
>>>>>>> ff9e8ff41cf574c5585b922b7e7156a9c134b26c
    var mMc: String? = null
    var mProblem: String? = null
    var mTimestamp: Long? = null
    var mRepairTimestamp: Long? = null
    var mKey:String? = null
    var mIssuedby:String? = null
    var mPic:String? = null
    var mPerbaikan:String? = null
    var mJenisproblem:String? = null
    var mFinishRepairTimestamp: Long? = null

<<<<<<< HEAD
    constructor() {

    }

=======
>>>>>>> ff9e8ff41cf574c5585b922b7e7156a9c134b26c
    constructor(finishrepairtimestamp: Long?,timestamp: Long?,repairtimestamp: Long?, mc: String?, problem: String?, key: String?,issuedby: String?,jenisproblem: String?,perbaikan: String?,pic: String?) {
        this.mTimestamp = timestamp
        this.mRepairTimestamp = repairtimestamp
        this.mMc = mc
        this.mProblem = problem
        this.mKey = key
        this.mIssuedby = issuedby
        this.mFinishRepairTimestamp = finishrepairtimestamp
        this.mPic = pic
        this.mPerbaikan = perbaikan
        this.mJenisproblem = jenisproblem
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