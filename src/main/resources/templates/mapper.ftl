<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.java.dao.${table.className}Dao">

	<sql id="tableColumns">
	<#assign columnField>
	<#list table.columnList as col>
		a.${col.columnName} AS "${col.attrName}",
	</#list>
	</#assign>
	${columnField?substring(0, columnField?last_index_of(","))}
	</sql>
	
	<sql id="fromTable">
		`${table.tableName}` a 
	</sql>
	
	<select id="get" resultType="${table.className}">
		SELECT 
			<include refid="tableColumns"/>
		FROM 
			`${table.tableName}` a 
		WHERE a.id = ${"#"}{id}
	</select>


    <select id="getForUpdate" resultType="${table.className}">
        SELECT
        <include refid="tableColumns"/>
        FROM
        `${table.tableName}` a
        WHERE a.id = ${"#"}{id} FOR UPDATE
    </select>

	
	<select id="getBy" resultType="${table.className}">
		SELECT 
			<include refid="tableColumns"/>
		FROM 
			`${table.tableName}` a 
		WHERE a.${"$"}{column} = ${"#"}{value}
	</select>
	
	<select id="fromTable" resultType="${table.className}">
		SELECT 
			<include refid="tableColumns"/>
		FROM 
			`${table.tableName}` a ${"$"}{sql }
	</select>
	
	<select id="findListSQL" resultType="java.util.HashMap">
		${"$"}{sql }
	</select>
	
    <select id="findList" resultType="${table.className}">
        SELECT 
	        <include refid="tableColumns"/>
        FROM 
        	<include refid="fromTable"/>
        <where>
        	<include refid="whereSQL"/>
        </where>
        <choose>
			<when test="orderBy != null and orderBy != ''">
				ORDER BY ${"$"}{orderBy}
			</when>
			<otherwise>
				ORDER BY a.create_date DESC
			</otherwise>
		</choose>
        <if test="first != null and max != null">
			LIMIT ${"#"}{first},${"#"}{max}
		</if>
    </select>
    
    <sql id="whereSQL">
    	<#list table.columnList as col>
    	<#if col.columnName != "id">
		<if test="${col.attrName} != null <#if col.javaType != "Date" && col.javaType != "Integer" && col.javaType != "Double">and ${col.attrName} != ''</#if>">
    		AND a.${col.columnName} = ${"#"}{${col.attrName}}
    	</if>
		</#if>
	    </#list>
	    <if test="createDateStart != null  and createDateEnd != null ">
    		AND a.create_date BETWEEN ${"#"}{createDateStart} AND ${"#"}{createDateEnd}
    	</if>
    	<if test="updateDateStart != null  and updateDateEnd != null ">
    		AND a.update_date BETWEEN ${"#"}{updateDateStart} AND ${"#"}{updateDateEnd}
    	</if>
    </sql>
    
    <insert id="insert">
		INSERT INTO ${table.tableName}(
			<#assign columnField>
			<#list table.columnList as col>
			<#if col.columnName == "desc">
			`${col.columnName}`,
			<#else>
			${col.columnName},
			</#if>
		    </#list>
		    </#assign>
		    ${columnField?substring(0, columnField?last_index_of(","))}
		) VALUES (
			<#assign columnField>
			<#list table.columnList as col>
        	${"#"}{${col.attrName}},
		    </#list>
		    </#assign>
		    ${columnField?substring(0, columnField?last_index_of(","))}
		)
	</insert>
	
	<update id="update">
		UPDATE ${table.tableName} SET 	
			<#assign columnField>
			<#list table.columnList as col>
			<#if col.columnName != "id">
			<#if col.columnName == "desc">
			`${col.columnName}` = ${"#"}{${col.attrName}},
			<#else>
			${col.columnName} = ${"#"}{${col.attrName}},
			</#if>
			</#if>
		    </#list>
		    </#assign>
		    ${columnField?substring(0, columnField?last_index_of(","))}
		WHERE id = ${"#"}{id}
	</update>
 	
 	<update id="delete">
		DELETE FROM ${table.tableName}
		WHERE id = ${"#"}{id}
	</update>
	
	<update id="exec">
		${"$"}{sql }
	</update>
	
	<select id="getCount" resultType="Integer">
		SELECT 
			count(1) 
		FROM 
			<include refid="fromTable"/>
		<where>
			<include refid="whereSQL"/>
		</where>
	</select>
    
</mapper>