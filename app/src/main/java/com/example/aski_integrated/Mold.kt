package com.example.aski_integrated

class Mold (var mold : String,
            var tech1: String,
            var tech2: String,
            var tech3: String,
            var tech4: String,
            var analisa: String,
            var problem: String,
            var jenisproblem: String,
            var estimasi: Long,
            var start: Long,
            var estimasijam: Long,
            var estimasimenit: Long,
            var key: String,
            var valueprogress: Int) {

    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        0,
        0,
        0,
        "",
        0) {


    }

}