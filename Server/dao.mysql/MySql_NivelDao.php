<?php
require_once "../dao.factory/Conexion.php";
class   MySql_NivelDao {     
  

  public function listar(){

    try {
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $lista=  array();
           $sql="select idNivel, descripcion from Nivel;";  
           
           $listaNivel= mysql_query($sql,$cn);
            
           while ($fila=  mysql_fetch_assoc($listaNivel)){
            $lista[]=$fila;               
           }     

       } catch (Exception $e) {     
            
       }     
     return  $lista;

  }

}
?>
