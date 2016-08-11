///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package iss.sa42.rest1;
//
//import java.util.Date;
//import java.util.UUID;
//import javax.enterprise.context.RequestScoped;
//import javax.servlet.annotation.WebServlet;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Response;
//import uno.Game;
//
///**
// *
// * @author MyatHtetAung
// */
//@RequestScoped
//@Path("/getnewgame")
////@WebServlet("/getnewgame")
//public class getNewGame {
//
//    @POST
//    @Produces("text/plain")
//
//    public Response hello(@FormParam("title") String title) {
//
//        String strgname = "ISS-TEST";
//        String strgid = UUID.randomUUID().toString().substring(0, 8);
//        Game g = new Game(strgid, strgname, "waiting");
//        
//        
//        return (Response.ok(strgid + " : " + strgname)
//                .build());
//        
//    }
//
//}
////