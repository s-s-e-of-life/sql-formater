package net.xx.sqlFormater.statement;

import org.springframework.beans.factory.InitializingBean;

import net.xx.sqlFormater.CommonBeanFactory;
import net.xx.sqlFormater.SqlBeanFactory;
import net.xx.sqlFormater.filter.GeneralFilterExecute;
import net.xx.sqlFormater.formater.GeneralFormaterDelete;
import net.xx.sqlFormater.util.InnerBeanFactory;

public class GeneralStatementDeleteWrapper implements GeneralStatementDelete, InitializingBean {

	/**
	 * 根据名称获取某类型的spring bean
	 * @param name bean名称
	 * @param clazz 数据类型
	 * @return spring bean
	 */
	protected static <T> T getBean(String name, Class<T> clazz) {
        return CommonBeanFactory.getBean(name, clazz);
    }
	
	private GeneralStatementDelete mSource;
	
	public GeneralStatementDeleteWrapper(GeneralFilterExecute filter) {
		this(InnerBeanFactory.createGeneralStatementDelete(filter));
	}
	
	public GeneralStatementDeleteWrapper(String filter, String formater) {
		this(InnerBeanFactory.createGeneralStatementDelete(filter, formater));
	}
	
	public GeneralStatementDeleteWrapper(GeneralStatementDelete source) {
		mSource = source;
	}
	
	public final GeneralStatementDelete getSource() {
		return mSource;
	}
	
	public void wiring() {}
	
	@Override
	public final void afterPropertiesSet() throws Exception {
		wiring();
		
		if(null == getGeneralFilterExecute()) {
			setGeneralFilterExecute(SqlBeanFactory.getBean("SqlGeneralFilterExecute", GeneralFilterExecute.class));
		}
		getGeneralFilterExecute().setGeneralStatementExecute(this);
		
		if(null == getGeneralFormaterDelete()) {
			setGeneralFormaterDelete(SqlBeanFactory.getBean("SqlFormaterDelete", GeneralFormaterDelete.class));
		}
		
		getGeneralFilterExecute().pretreatment();
		pretreatment();
	}

	@Override
	public GeneralFilterExecute getGeneralFilterExecute() {
		return getSource().getGeneralFilterExecute();
	}

	@Override
	public void setGeneralFilterExecute(GeneralFilterExecute generalFilterExecute) {
		getSource().setGeneralFilterExecute(generalFilterExecute);
	}

	@Override
	public GeneralFormaterDelete getGeneralFormaterDelete() {
		return getSource().getGeneralFormaterDelete();
	}

	@Override
	public void setGeneralFormaterDelete(GeneralFormaterDelete generalFormaterDelete) {
		getSource().setGeneralFormaterDelete(generalFormaterDelete);
	}

	@Override
	public void pretreatment() {
		getSource().pretreatment();
	}

	@Override
	public void setRawFrom(String sql) {
		getSource().setRawFrom(sql);
	}

	@Override
	public void pushRawCondition(String sql) {
		getSource().pushRawCondition(sql);
	}

	@Override
	public void pushRawArg(Object arg) {
		getSource().pushRawArg(arg);
	}

	@Override
	public void mergeRawArg(Object[] args) {
		getSource().mergeRawArg(args);
	}

	@Override
	public String getSql() {
		return getSource().getSql();
	}

	@Override
	public Object[] getArgs() {
		return getSource().getArgs();
	}

	@Override
	public int execute() {
		return getSource().execute();
	}

	@Override
	public String getOpenQuote() {
		return getSource().getOpenQuote();
	}

	@Override
	public String getCloseQuote() {
		return getSource().getCloseQuote();
	}

	@Override
	public String quoted(String text) {
		return getSource().quoted(text);
	}

	@Override
	public String quoted(String table, String column) {
		return getSource().quoted(table, column);
	}

}