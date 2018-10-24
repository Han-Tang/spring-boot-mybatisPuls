package cn.xunhang.system.entity;

import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 针对具体编号生成的树结构
 * </p>
 *
 * @author tyj
 * @since 2018-09-13
 */
@TableName("BankAccount")
public class BankAccount  extends InfoBaseDO<BankAccount> {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
	private String code;
    /**
     * 公司名称
     */
	private String name;
    /**
     * 开户银行
     */
	private String bank;
    /**
     * 开户账号
     */
	private String account;
    /**
     * 收款账号名称
     */
	private String collectionAccount;
    /**
     * 账户余额
     */
	private String balance;
    /**
     * 分行信息
     */
	private String branch;
    /**
     * 开户名称
     */
	private String accountName;
    /**
     * 税号
     */
	private String tax;
    /**
     * 排序
     */
	private Integer orderNum;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCollectionAccount() {
		return collectionAccount;
	}

	public void setCollectionAccount(String collectionAccount) {
		this.collectionAccount = collectionAccount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	@Override
	public String toString() {
		return "BankAccount{" +
			", id=" + id +
			", code=" + code +
			", name=" + name +
			", bank=" + bank +
			", account=" + account +
			", collectionAccount=" + collectionAccount +
			", balance=" + balance +
			", branch=" + branch +
			", accountName=" + accountName +
			", tax=" + tax +
			", orderNum=" + orderNum +
			", active=" + active +
			", deleted=" + deleted +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", ts=" + ts +
			"}";
	}
}
