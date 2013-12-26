<?php
require_once "../dao.factory/Conexion.php";
class   MySql_GradoDao {     
  

  public function listar(){

    try {
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $lista['GRADOS']=  array();
           $sql="select N.idNivel as idNivel, N.descripcion as nivel, G.idGrado as idGrado, G.descripcion as grado, 
                  P.idProfesor as idProfesor, concat_ws(' ',P.nom , P.ape) as nombre, P.sueldo as sueldo from Grado G 
                  inner join Profesor P on G.idProfesor=P.idProfesor 
                  inner join Nivel N on G.idNivel=N.idNivel order by G.idNivel, G.idGrado;";  
           
           $listaGrado= mysql_query($sql,$cn);
            
           while ($fila=  mysql_fetch_assoc($listaGrado)){
            array_push($lista['GRADOS'], 
             array('idNivel'=>$fila['idNivel'],'nivel'=>$fila['nivel'],
                   'idGrado'=>$fila['idGrado'],'grado'=>$fila['grado'],
                   'idProfesor'=>$fila['idProfesor'],'nombre'=>$fila['nombre'],
                   'sueldo'=>$fila['sueldo']));                  
           }     

       } catch (Exception $e) {     
            
       }     
     return  $lista;

  }

  public function agregar($nom, $ape, $sueldo, $idNivel, $idGrado){

      try{
             $objc = new ConexionDB();
             $cn=$objc->getConexionDB();
             $sql1="insert into Profesor(nom, ape, sueldo) values('$nom', '$ape', $sueldo);";
             $sql2="update Grado set idProfesor=(select max(idProfesor) from Profesor) where idNivel=$idNivel and idGrado=$idGrado;";
        
             $estado1 =  mysql_query($sql1,$cn);
             $estado2 =  mysql_query($sql2,$cn);
             
             return $estado1+$estado2;

          }catch(Exception $e){ 
                return 0;
          }

    }

  public function eliminar($idProfesor, $idNivel, $idGrado){

    try{
           $objc = new ConexionDB();
           $cn=$objc->getConexionDB();
           $sql1="update Grado set idProfesor=0 where idNivel=$idNivel and idGrado=$idGrado;";
           $sql2="delete from Profesor where idProfesor=$idProfesor;";
        
           $estado1 =  mysql_query($sql1,$cn);
           $estado2 =  mysql_query($sql2,$cn);
             
           return $estado1+$estado2;

        }catch(Exception $e){
              return 0;
        }

  }



}
?>
