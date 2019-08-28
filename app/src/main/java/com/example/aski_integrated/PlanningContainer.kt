package com.example.aski_integrated

class PlanningContainer {
    var mnomold: String? = null
    var mKeypl:String? = null
    var mtech1:String? = null
    var mtech2:String? = null
    var mtech3:String? = null
    var mtech4:String? = null
    var mAnalisapl:String? = null
    var mProblemETpl:String? = null
    var mjenisProblemETpl:String? = null
    var estimasipl:Long? = null
    var mstart:Long? = null

    constructor( mnomold :String?, tech1: String?, tech2: String?, tech3: String?, tech4: String?,analisa: String?,problem: String?, jenisproblem: String?, estimasi: Long?,start: Long?, key:String?)

    {
        this.mnomold = mnomold
        this.mKeypl = key
        this.mtech1= tech1
        this.mtech2 = tech2
        this.mtech3 = tech3
        this.mtech4 = tech4
        this.mAnalisapl = analisa
        this.mProblemETpl = problem
        this.mjenisProblemETpl = jenisproblem
        this.estimasipl = estimasi
        this.mstart = start
    }

    fun getemstart(): Long? {
        return mstart
    }

    fun setmstart(start: Long?) {
        mstart = start
    }

    fun getestimasipl(): Long? {
        return estimasipl
    }

    fun setestimasipl(estimasi: Long?) {
        estimasipl = estimasi
    }

    fun getmKeypl(): String? {
        return mKeypl
    }

    fun setmKeypl(key: String?) {
        mKeypl = key
    }

    fun getProbelemETpl(): String? {
        return mProblemETpl
    }

    fun setProblemETpl(problem: String?) {
        mProblemETpl = problem
    }

    fun getjenisProblemETpl(): String? {
        return mjenisProblemETpl
    }

    fun setjenisProblemETpl(jenisproblem: String?) {
        mjenisProblemETpl = jenisproblem
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
    fun getKeypl(): String? {
        return mKeypl
    }

    fun setKeypl(keypl: String?) {
        mKeypl = keypl
    }

    fun getNoMoldpl(): String? {
        return mnomold
    }

    fun setNoMoldpl(nomold: String?) {
        mnomold = mnomold
    }


    fun getAnalisapl(): String? {
        return mAnalisapl
    }

    fun setAnalisapl(analisapl: String?) {
        mAnalisapl = analisapl
    }

}
