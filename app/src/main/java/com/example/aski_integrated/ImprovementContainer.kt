package com.example.aski_integrated

class ImprovementContainer {

    var mnomold: String? = null
    var mKey: String? = null
    var mtech1: String? = null
    var mtech2: String? = null
    var mtech3: String? = null
    var mtech4: String? = null
    var mAnalisa: String? = null
    var mProblemET: String? = null
    var mjenisProblemET: String? = null
    var mestimasi: Long? = null
    var mstart: Long? = null
    var mestimasijam: Long? = null
    var mestimasimenit: Long? = null
    var mvalueprogress : Int? = null

    constructor(
        mnomold: String?,
        tech1: String?,
        tech2: String?,
        tech3: String?,
        tech4: String?,
        analisa: String?,
        problem: String?,
        jenisproblem: String?,
        estimasi: Long?,
        start: Long?,
        estimasijam: Long?,
        estimasimenit: Long?,
        key: String?,
        valueprogress: Int?

    ) {
        this.mnomold = mnomold
        this.mKey = key
        this.mtech1 = tech1
        this.mtech2 = tech2
        this.mtech3 = tech3
        this.mtech4 = tech4
        this.mAnalisa = analisa
        this.mProblemET = problem
        this.mjenisProblemET = jenisproblem
        this.mestimasi = estimasi
        this.mstart = start
        this.mestimasijam = estimasijam
        this.mestimasimenit = estimasimenit
        this.mvalueprogress = valueprogress
    }

    fun getemstart(): Long? {
        return mstart
    }

    fun setmstart(start: Long?) {
        mstart = start
    }

    fun getmestimasi(): Long? {
        return mestimasi
    }

    fun setmestimasi(estimasi: Long?) {
        mestimasi = estimasi
    }

    fun getmestimasijam(): Long? {
        return mestimasijam
    }

    fun setmestimasijam(estimasijam: Long?) {
        mestimasijam = estimasijam
    }

    fun getmestimasimenit(): Long? {
        return mestimasimenit
    }

    fun setmestimasimenit(estimasimenit: Long?) {
        mestimasimenit = estimasimenit
    }

    fun getKey(): String? {
        return mKey
    }

    fun setKey(key: String?) {
        mKey = key
    }

    fun getmProbelemET(): String? {
        return mProblemET
    }

    fun setmProblemET(problem: String?) {
        mProblemET = problem
    }

    fun getmjenisProblemET(): String? {
        return mjenisProblemET
    }

    fun setmjenisProblemET(jenisproblem: String?) {
        mjenisProblemET = jenisproblem
    }

    fun getmtech1(): String? {
        return mtech1
    }

    fun setmtech1(tech1: String?) {
        mtech1 = tech1
    }

    fun getmtech2(): String? {
        return mtech2
    }

    fun setmtech2(tech2: String?) {
        mtech2 = tech2
    }

    fun getmtech3(): String? {
        return mtech3
    }

    fun setmtech3(tech3: String?) {
        mtech3 = tech3
    }

    fun getmtech4(): String? {
        return mtech4
    }

    fun setmtech4(tech4: String?) {
        mtech4 = tech4
    }

    fun getmKey(): String? {
        return mKey
    }

    fun setmKey(key: String?) {
        mKey = key
    }

    fun getNoMold(): String? {
        return mnomold
    }

    fun setNoMold(nomold: String?) {
        mnomold = nomold
    }


    fun getAnalisa(): String? {
        return mAnalisa
    }

    fun setAnalisa(analisa: String?) {
        mAnalisa = analisa
    }


    fun getmvalueprogress (): Int? {
        return mvalueprogress
    }


    fun setmvalueprogress (valueprogress: Int?) {
        mvalueprogress = valueprogress
    }
}