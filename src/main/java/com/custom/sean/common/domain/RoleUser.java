/**
 * 
 */
package com.custom.sean.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色用户关系表
 * 
 * @author sean
 *
 */
@Entity
@Table(name = "auth_role_user")
@Getter
@Setter
@EqualsAndHashCode
public class RoleUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "id", strategy = "uuid2")
	@GeneratedValue(generator = "id")
	private String id;
	/**
	 * 角色
	 */
	@ManyToOne
	private Role role;
	/**
	 * 账户
	 */
	@ManyToOne
	private User user;

}
