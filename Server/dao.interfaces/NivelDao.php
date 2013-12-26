<?php
     require_once "../dao.mysql/MySql_NivelDao.php";
 	
     	$key =$_REQUEST['key'];

     switch ($key) {
		    case 1: //LISTAR
		    	
		    	$objeto=new  MySql_NivelDao();
       			$LISTA= $objeto->listar();
		    	
		    	header('Content-type: application/json');
		    	echo json_encode($LISTA);

		        break;
		}

    
?>