package pro.ahoora.zhin.webserver

import android.util.Log
import fi.iki.elonen.NanoHTTPD

class NanoServer : NanoHTTPD {

    constructor(port: Int) : super(port) {}

    constructor(hostname: String, port: Int) : super(hostname, port) {}

    override fun serve(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {

        val map = HashMap<String, String>()
        session.parseBody(map)

        Log.e("method", session.method.toString())

        if (session.method == Method.POST || session.method == Method.GET) {
            Log.e("uri", session.uri)
            Log.e("params", session.parms.toString())
            Log.e("s", session.queryParameterString)
            Log.e("ss", session.cookies.toString())
            Log.e("sss", session.headers.toString())
            Log.e("params", map.toString())
        }

        var msg = "<html><body><h1>Hello server</h1>\n"
        val parms = session.parms
        if (parms["username"] == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n"
        } else {
            msg += "<p>Hello, " + parms["username"] + "!</p>"
        }
        return NanoHTTPD.newFixedLengthResponse("$msg</body></html>\n")
    }
}