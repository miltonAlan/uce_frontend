///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package test;
//
//import Entidades.AfUsuario;
//import Managed.ManagedLogin;
//import javax.faces.context.FacesContext;
//
///**
// *
// * @author mpaucar
// */
//public class test {
//    public void prueba(){
//    FacesContext facesContext = FacesContext.getCurrentInstance();
//        AfUsuario afUsuarioSesion = (AfUsuario) facesContext.getExternalContext().getSessionMap().get(ManagedLogin.usuarioSesionInvitado);
//        System.out.println("XXXXXXX: " + afUsuarioSesion);
//        System.out.println("XXXXXXXXXXXXxxafUsuarioSesion: " + afUsuarioSesion.getAuLogin());
//        AfUsuario afUsuarioEncontrado = manejadorAfUsuario.buscarPorLogin(afUsuarioSesion.getAuLogin());
//        System.out.println("XXXXXXX: " + afUsuarioEncontrado);
//        System.out.println("listaActivosACargo." + manejadorAfActivoFijo.buscarPorConsecutivo(afUsuarioEncontrado));
////        this.setListaActivosFijosACargo(manejadorAfActivoFijo.buscarPorConsecutivo(afUsuarioEncontrado.getAuConsecutivo()));
//    }
//    
//}
