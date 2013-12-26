<?php
class ConexionDB 
{
     const SERVER = "tu_ip";
     const USER = "tu_usuario";
     const PASS = "";
     const DATABASE = "ColeDB";
     private $cn = null;
     public function getConexionDB(){
    	if( $this->cn == null){
        try{
	       	$this->cn = @mysql_connect(self::SERVER, self::USER, self::PASS);
                if( mysql_errno() != 0 ){
                	throw new Exception("No se tiene acceso al servidor");
                }
                @mysql_select_db(self::DATABASE, $this->cn);
                if( mysql_errno() != 0 ){
                	throw new Exception("Base de datos no existe");
                }
    		}catch(Exception $e){
            	throw $e;
            }        
        }
        return $this->cn;
    }      
}
?>
