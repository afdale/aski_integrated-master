package com.example.aski_integrated

class BreakdownContainer {


    var mnomold: String? = null
    var mKeybr:String? = null
    var mtech1:String? = null
    var mtech2:String? = null
    var mtech3:String? = null
    var mtech4:String? = null
    var mAnalisabr:String? = null
    var mProblemETbr:String? = null
    var mjenisProblemETbr:String? = null
    var estimasibr:Long? = null
    var mstart:Long? = null
    var mestimasijam:Long? = null
    var mestimasimenit:Long? = null

    constructor( mnomold :String?,start: Long?, key:String?, tech1: String?, tech2: String?, tech3: String?, tech4: String?,analisa: String?,problem: String?, jenisproblem: String?, estimasi: Long?, estimasijam: Long?, estimasimenit: Long?)

    {
        this.mnomold = mnomold
        this.mKeybr = key
        this.mtech1= tech1
        this.mtech2 = tech2
        this.mtech3 = tech3
        this.mtech4 = tech4
        this.mAnalisabr = analisa
        this.mProblemETbr = problem
        this.mjenisProblemETbr = jenisproblem
        this.estimasibr = estimasi
        this.mstart = start
        this.mestimasijam = estimasijam
        this.mestimasimenit = estimasimenit
    }

    fun getemstart(): Long? {
        return mstart
    }

    fun setmstart(start: Long?) {
        mstart = start
    }

    fun getestimasibr(): Long? {
        return estimasibr
    }

    fun setestimasibr(estimasi: Long?) {
        estimasibr = estimasi
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

    fun getmKeybr(): String? {
        return mKeybr
    }

    fun setmKeybr(key: String?) {
        mKeybr = key
    }

    fun getProbelemETbr(): String? {
        return mProblemETbr
    }

    fun setProblemETbr(problem: String?) {
        mProblemETbr = problem
    }

    fun getjenisProblemETbr(): String? {
        return mjenisProblemETbr
    }

    fun setjenisProblemETbr(jenisproblem: String?) {
        mjenisProblemETbr = jenisproblem
    }

    fun gettech1(): String? {
        return mtech1
    }

    fun settech1(tech1: String?) {
        mtech1 = tech1
    }

    fun gettech2(): String? {
        return mtech2
    }

    fun settech2(tech2: String?) {
        mtech2 = tech2
    }

    fun gettech3(): String? {
        return mtech3
    }

    fun settech3(tech3: String?) {
        mtech3 = tech3
    }
    fun gettech4(): String? {
        return mtech4
    }

    fun settech4(tech4: String?) {
        mtech4 = tech4
    }
    fun getKeybr(): String? {
        return mKeybr
    }

    fun setKeybr(keybr: String?) {
        mKeybr = keybr
    }

    fun getNoMoldbr(): String? {
        return mnomold
    }

    fun setNoMoldbr(nomold: String?) {
        mnomold = mnomold
    }


    fun getAnalisabr(): String? {
        return mAnalisabr
    }

    fun setAnalisabr(analisabr: String?) {
        mAnalisabr = analisabr
    }

}