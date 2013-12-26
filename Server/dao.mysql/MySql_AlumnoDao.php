<?php
require_once "../dao.factory/Conexion.php";
class   MySql_AlumnoDao {     
  

  public function agregar($nom, $ape, $fechNac, $idNivel, $idGrado){

    try{
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $sql="insert into Alumno(nom, ape, fechNac, idNivel, idGrado)  values('$nom', '$ape', '$fechNac', $idNivel, $idGrado);";

           $estado=  mysql_query($sql,$cn);
           return $estado;

        }catch(Exception $e){ 
              return 0;
        }

  }

  public function actualizar($idAlumno, $nom, $ape, $fechNac, $idNivel, $idGrado){

    try{
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $sql="update Alumno set nom='$nom', ape='$ape', fechNac='$fechNac', idNivel=$idNivel, idGrado=$idGrado where idAlumno=$idAlumno;";

           $estado=  mysql_query($sql,$cn);
           return $estado;

        }catch(Exception $e){
              return 0;
        }

  }

  public function eliminar($idAlumno){

    try{
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $sql="delete from Alumno where idAlumno=$idAlumno;";

           $estado=  mysql_query($sql,$cn);
           return $estado;

        }catch(Exception $e){
              return 0;
        }

  }

  public function listar($idNivel, $idGrado){

    try {

           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $lista['ALUMNOS']=  array();
           $sql="select idAlumno, nom, ape, fechNac from Alumno where idNivel=$idNivel and idGrado=$idGrado;";  
           
           $listaAlumno= mysql_query($sql,$cn);
            
           while ($fila=  mysql_fetch_assoc($listaAlumno)){
            array_push($lista['ALUMNOS'], 
             array('idAlumno'=>$fila['idAlumno'],'nom'=>$fila['nom'],'ape'=>$fila['ape'],'fechNac'=>$fila['fechNac']));                  
           }     

       } catch (Exception $e) {     
            
       }     
     return  $lista;

  }

  public function listarAlumno($idAlumno){

    try {

           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $lista['ALUMNO']=  array();
           $sql="select nom, ape, fechNac, idNivel, idGrado from Alumno where idAlumno=$idAlumno;";  
           
           $listaAlumno= mysql_query($sql,$cn);
            
           while ($fila=  mysql_fetch_assoc($listaAlumno)){
            array_push($lista['ALUMNO'], 
             array('nom'=>$fila['nom'],'ape'=>$fila['ape'],'fechNac'=>$fila['fechNac'],'idNivel'=>$fila['idNivel'],'idGrado'=>$fila['idGrado']));               
           }     

       } catch (Exception $e) {     
            
       }     
     return  $lista;

  }

}
?>
