package com.bancoexterior.parametros.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bancoexterior.parametros.clientes.entities.ClientesPersonalizados;

public interface IClientesPersonalizadosRepository extends JpaRepository<ClientesPersonalizados, String>{

	String queryNativo = "SELECT cod_ibs, nro_id_cliente, nombre_rif, flag_activo, cod_usuario, fecha_modificacion, fecha_ingreso "
			+ "FROM \"Convenio1\".\"Clientes_personalizados\" "
			+ "where cod_ibs= (case when ?1 = '' then cod_ibs else ?1 end) "
			+ "and nro_id_cliente = (case when ?2 = '' then nro_id_cliente else ?2 end) "
			+ "and nombre_rif = (case when ?3 = '' then nombre_rif else ?3 end) "
			+ "and "
			+ "	case when  ?4 = 'si' then		"
			+ "		flag_activo= ?5 "
			+ "	else 	"
			+ "		flag_activo = flag_activo "
			+ "	end";
	
	@Query(value = queryNativo, nativeQuery = true)
	public List<ClientesPersonalizados> getClientesPersonalizadosByNativo(String codIbs, String nroIdCliente, String nombreRif, String flag, boolean flagActivo);
}
