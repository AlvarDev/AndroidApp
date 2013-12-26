
<?php
require_once "../dao.factory/Conexion.php";
class   MySql_AccesoDao{  

 public function login($usu,$cla){
     
      try{  
      		 $objc = new ConexionDB();
           $cn=$objc->getConexionDB();              
           $response=  array(); 
           $response["nombre"] = "no";
           $login= mysql_query(" select nom from Acceso where idAcceso='$usu' and contra='$cla';",$cn);

      			while ($row = mysql_fetch_array($login)) {
      			       $response["nombre"] = "".$row[0];
      			}

        }catch(Exception $e){
                   $response["nombre"] = "Error en Login";
        }
     return $response;
    }  


}
?>
