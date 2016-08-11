/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.sa42.rest1;

import uno.Card;
import uno.Game;
import uno.Player;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import java.net.URI;

@RequestScoped
@Path("/game")
public class games {

    public static Map<String, Game> hashmapgame = new HashMap<String, Game>();
    public static String UgameID = "";
    public static String UplayerID = "";
    public static Player playing = null;
    private final String port = "http://localhost:8383";

    @POST
    @Path("/newgame")
    @Produces("text/plain")
    public Response addGame(@FormParam("gname") String name) {

        String strgname = name;
        String strgid = UUID.randomUUID().toString().substring(0, 8);
        UgameID = strgid;
        Game g = new Game(strgid, strgname, "waiting");

        hashmapgame.put(strgid, g);

        try {
            URI location = new java.net.URI(port + "/playuno/pendinggame.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }
    }

    @GET
    @Path("/gamelist")
    @Produces("text/plain")
    public Response listGame() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();

        Iterator entries = hashmapgame.entrySet().iterator();
        while (entries.hasNext()) {
            Entry thisEntry = (Entry) entries.next();
            Object key = thisEntry.getKey();
            Object value = thisEntry.getValue();

            jso = Json.createObjectBuilder()
                    .add("gid", thisEntry.getKey().toString())
                    .add("name", ((Game) thisEntry.getValue()).getGname())
                    .add("status", ((Game) thisEntry.getValue()).getStatus())
                    .build();

            jsa.add(jso);
        }

        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    @GET
    @Path("/pending")
    @Produces("text/plain")
    public Response newGame() {
        JsonObject jso;

        Game g = hashmapgame.get(UgameID);

        jso = Json.createObjectBuilder()
                .add("gid", g.getId())
                .add("name", g.getGname())
                .add("status", g.getStatus())
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    @POST
    @Path("/joingame")
    @Produces("text/plain")
    public Response join(@FormParam("gid") String gid, @FormParam("pname") String pname) {

        UgameID = gid;
        String strpname = pname;
        String strpid = UUID.randomUUID().toString().substring(0, 8);

        Player p = new Player(strpid, strpname);
        UplayerID = strpid;

        hashmapgame.get(UgameID).addPlayer(p);

        try {
            URI location = new java.net.URI(port + "/playuno/pendingplayer.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }
    }

    @GET
    @Path("/playerlist")
    @Produces("text/plain")
    public Response listPlayer() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();

        Game g = hashmapgame.get(UgameID);

        ArrayList<Player> pList = g.getPlayList();

        for (int i = 0; i < pList.size(); i++) {
            Player player = pList.get(i);

            jso = Json.createObjectBuilder()
                    .add("pid", player.getId())
                    .add("name", player.getName())
                    //.add("status", g.getStatus())
                    .build();

            jsa.add(jso);

        }

        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    @GET
    @Path("/playerpending")
    @Produces("text/plain")
    public Response showTablePlayer() {
        JsonObject jso;

        Game g = hashmapgame.get(UgameID);

        jso = Json.createObjectBuilder()
                .add("gid", g.getId())
                .add("gname", g.getGname())
                .add("pid", UplayerID)
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    @POST
    @Path("/gamestart")
    @Produces("text/plain")
    public Response startTablePlay(@FormParam("gid") String gid) {

        UgameID = gid;

        Game g = hashmapgame.get(UgameID);
        g.startPlay();

        try {
            URI location = new java.net.URI(port + "/playuno/gamestart.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }
    }

    @GET
    @Path("/discardcard")
    @Produces("text/plain")
    public Response showTableCards() {
        JsonObject jso;

        Game g = hashmapgame.get(UgameID);

        Card card = g.getDiscardPile();

        jso = Json.createObjectBuilder()
                .add("gid", g.getId())
                .add("gname", g.getGname())
                .add("color", card.getColor())
                .add("type", card.getType())
                .add("value", card.getValue())
                .add("image", card.getImage())
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    @POST
    @Path("/plyerstartgame")
    @Produces("text/plain")
    public Response startPlayerPlay(@FormParam("gid") String gid, @FormParam("pid") String pid) {

        UgameID = gid;
        UplayerID = pid;

        try {
            URI location = new java.net.URI(port + "/playuno/playunogo.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }
    }

    @GET
    @Path("/spreadcard")
    @Produces("text/plain")
    public Response showPlayerCards() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();
        Player player = null;
        Game g = hashmapgame.get(UgameID);

        ArrayList<Player> pList = g.getPlayList();
        for (int i = 0; i < pList.size(); i++) {

            player = pList.get(i);
            if (player.getId().equals(UplayerID)) {
                break;
            } else {
                player = null;
            }

        }

        ArrayList<Card> cardinhand = player.getCardInHand();

        for (int i = 0; i < cardinhand.size(); i++) {
            Card card = cardinhand.get(i);

            jso = Json.createObjectBuilder()
                    .add("color", card.getColor())
                    .add("type", card.getType())
                    .add("value", card.getValue())
                    .add("image", card.getImage())
                    .build();

            jsa.add(jso);

        }

        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();

        return resp;
    }

}
