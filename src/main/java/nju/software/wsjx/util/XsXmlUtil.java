package nju.software.wsjx.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import nju.software.wsjx.service.model.xs.ZmModel;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class XsXmlUtil {
	public static void BuildXMLDoc(WsfdModel wsfdModel,XsPjjgModel pjjgModel,String outputpath,String filename,String path) throws IOException, JDOMException {     
		FileUtil fileUtil = new FileUtil();  

		String specialpath="C:\\Users\\super\\Desktop\\";

		// 创建根节点 全文 ,若全文为空，则将文书提取放入special文件夹
		if(wsfdModel.getQw()!=null){		 
			Element root = new Element("QW").setAttribute("value", wsfdModel.getQw());     
			root.setAttribute("nameCN","全文");
			// 将根节点添加到文档中；     
			Document Doc = new Document(root);   

			if(wsfdModel.getCpjg()!=null){
				Element pjjg=new Element ("PJJG").setAttribute("value",wsfdModel.getCpjg()); 
				pjjg.setAttribute("nameCN","判决结果");
				root.addContent(pjjg);
//				是否提出管辖权异议
				if(!StringUtil.isBlank(pjjgModel.getTcgxyy())){
					Element sftcggxqyy=new Element ("SFTCGXQYY").setAttribute("value",pjjgModel.getTcgxyy()); 
					sftcggxqyy.setAttribute("nameCN","提出管辖权异议");
					pjjg.addContent(sftcggxqyy);
				}
//				结案方式
				if(!StringUtil.isBlank(pjjgModel.getJafs())){
					Element jafs=new Element ("JAFS").setAttribute("value",pjjgModel.getJafs()); 
					jafs.setAttribute("nameCN","结案方式");
					pjjg.addContent(jafs);
				}
//				一审刑事部分判决结果
				if(!StringUtil.isBlank(pjjgModel.getYsxsbfpjjg())){
					Element ysxsbf=new Element ("YSXSBFPJJG").setAttribute("value",pjjgModel.getYsxsbfpjjg()); 
					ysxsbf.setAttribute("nameCN","一审刑事部分判决解雇");
					pjjg.addContent(ysxsbf);
				}
//				附带民事部分裁判结果
				if(!StringUtil.isBlank(pjjgModel.getFdmscpjg())){
					Element ysxsbf=new Element ("FDMSBFCPJG").setAttribute("value",pjjgModel.getFdmscpjg()); 
					ysxsbf.setAttribute("nameCN","附带民事部分裁判结果");
					pjjg.addContent(ysxsbf);
				}
//				刑事判决结果分组
				if(pjjgModel.getPjjgfzModels()!=null && pjjgModel.getPjjgfzModels().size()>0){
					for(XspjjgfzModel fzModel:pjjgModel.getPjjgfzModels()){
						if(!StringUtil.isBlank(fzModel.getEslxjg()) || !StringUtil.isBlank(fzModel.getEslxjg())){
							Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","刑事判决结果分组");
							pjjg.addContent(fz);
							
							if(fzModel.getSscyr()!=null){
								Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
								sscyr.setAttribute("nameCN","诉讼参与人");
								fz.addContent(sscyr);
							}
							
							Element sscyr=new Element ("BSPJJG").setAttribute("nameCN","本审判决结果");
							fz.addContent(sscyr);
							
							if(!StringUtil.isBlank(fzModel.getEslxjg())){
								Element e=new Element ("ESLXJG").setAttribute("value",fzModel.getEslxjg()); 
								e.setAttribute("nameCN","二审量刑结果");
								sscyr.addContent(e);
							}
							if(!StringUtil.isBlank(fzModel.getEslxjg())){
								Element e=new Element ("ESSLJG").setAttribute("value",fzModel.getEssljg()); 
								e.setAttribute("nameCN","二审审理结果");
								sscyr.addContent(e);
							}
						}else{
							Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","刑事判决结果分组");
							pjjg.addContent(fz);
							if(fzModel.getSscyr()!=null){
								Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
								sscyr.setAttribute("nameCN","诉讼参与人");
								fz.addContent(sscyr);
							}
							if(fzModel.getDzpf()!=null){
								for(PfModel pf:fzModel.getDzpf()){
									Element dzpf=new Element ("DZPF").setAttribute("value",pf.getPfnr()); 
									dzpf.setAttribute("nameCN","单罪判罚");
									fz.addContent(dzpf);
									if(pf.getZm()!=null){
										Element zm=new Element ("ZM").setAttribute("value",pf.getZm().getZm()); 
										zm.setAttribute("nameCN","罪名");
										dzpf.addContent(zm);
									}
									if(pf.getZx()!=null){
										Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
										dzpf.addContent(zx);
										if(pf.getZx().getZxlb()!=null){
											Element zxlb=new Element ("ZXLB").setAttribute("value",pf.getZx().getZxlb()); 
											zxlb.setAttribute("nameCN","主刑类别");
											zx.addContent(zxlb);
										}
										if(pf.getZx().getZxxq()!=null){
											Element zxlb=new Element ("ZXQX").setAttribute("value",pf.getZx().getZxxq()); 
											zxlb.setAttribute("nameCN","主刑期限");
											zx.addContent(zxlb);
										}
									}
									if(pf.getFjxList()!=null){
										for(FjxModel fjx:pf.getFjxList()){
											Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
											dzpf.addContent(fj);
											if(fjx.getLb()!=null){
												Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
												lb.setAttribute("nameCN","附加刑类别");
												fj.addContent(lb);
												if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
													if(fjx.getSe()!=null){
														Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
														se.setAttribute("nameCN","数额");
														lb.addContent(se);
													}
													if(fjx.getBz()!=null){
														Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
														bz.setAttribute("nameCN","币种");
														lb.addContent(bz);
													}
												}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
													if(fjx.getQx()!=null){
														Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
														qx.setAttribute("nameCN","期限");
														lb.addContent(qx);
													}
												}
											}
										}
									}
									if(pf.getHx()!=null){
										Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
										dzpf.addContent(hx);
										if(pf.getHx().getZxlb()!=null){
											Element lb=new Element ("HXLB").setAttribute("value",pf.getHx().getZxlb()); 
											lb.setAttribute("nameCN","缓刑类别");
											hx.addContent(lb);
										}
										if(pf.getHx().getZxxq()!=null){
											Element lb=new Element ("QX").setAttribute("value",pf.getHx().getZxxq()); 
											lb.setAttribute("nameCN","缓刑期限");
											hx.addContent(lb);
										}
									}
									if(pf.getPjjglx()!=null){
										Element pjjglx=new Element ("PJJGLX").setAttribute("value",pf.getPjjglx()); 
										pjjglx.setAttribute("nameCN","判决结果类型");
										dzpf.addContent(pjjglx);
									}
								}
							}

							if(fzModel.getZxpf()!=null){
								Element zxpf=new Element ("ZXPF").setAttribute("value",fzModel.getZxpf().getPfnr()); 
								zxpf.setAttribute("nameCN","执行判罚");
								fz.addContent(zxpf);
								if(fzModel.getZxpf().getZx()!=null){
									Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
									zxpf.addContent(zx);
									if(fzModel.getZxpf().getZx().getZxlb()!=null){
										Element zxlb=new Element ("ZXLB").setAttribute("value",fzModel.getZxpf().getZx().getZxlb()); 
										zxlb.setAttribute("nameCN","主刑类别");
										zx.addContent(zxlb);
									}
									if(fzModel.getZxpf().getZx().getZxxq()!=null){
										Element zxlb=new Element ("ZXQX").setAttribute("value",fzModel.getZxpf().getZx().getZxxq()); 
										zxlb.setAttribute("nameCN","主刑期限");
										zx.addContent(zxlb);
									}
								}
								if(fzModel.getZxpf().getFjxList()!=null){
									for(FjxModel fjx:fzModel.getZxpf().getFjxList()){
										Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
										zxpf.addContent(fj);
										if(fjx.getLb()!=null){
											Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
											lb.setAttribute("nameCN","附加刑类别");
											fj.addContent(lb);
											if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
												if(fjx.getSe()!=null){
													Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
													se.setAttribute("nameCN","数额");
													lb.addContent(se);
												}
												if(fjx.getBz()!=null){
													Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
													bz.setAttribute("nameCN","币种");
													lb.addContent(bz);
												}
											}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
												if(fjx.getQx()!=null){
													Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
													qx.setAttribute("nameCN","期限");
													lb.addContent(qx);
												}
											}
										}
									}
								}
								if(fzModel.getZxpf().getHx()!=null){
									Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
									zxpf.addContent(hx);
									if(fzModel.getZxpf().getHx().getZxlb()!=null){
										Element lb=new Element ("HXLB").setAttribute("value",fzModel.getZxpf().getHx().getZxlb()); 
										lb.setAttribute("nameCN","缓刑类别");
										hx.addContent(lb);
									}
									if(fzModel.getZxpf().getHx().getZxxq()!=null){
										Element lb=new Element ("QX").setAttribute("value",fzModel.getZxpf().getHx().getZxxq()); 
										lb.setAttribute("nameCN","缓刑期限");
										hx.addContent(lb);
									}
								}
								if(fzModel.getZxpf().getPjjglx()!=null){
									Element pjjglx=new Element ("PJJGLX").setAttribute("value",fzModel.getZxpf().getPjjglx()); 
									pjjglx.setAttribute("nameCN","判决结果类型");
									zxpf.addContent(pjjglx);
								}
							}
							if(fzModel.getPjzzm()!=null){
								ZmModel pjzzmModel = fzModel.getPjzzm();
								if(pjzzmModel.getZm()!=null){
									Element pjzzm=new Element ("PJZZM").setAttribute("value",pjzzmModel.getZm()); 
									pjzzm.setAttribute("nameCN","判决主罪名");
									fz.addContent(pjzzm);
								}
							}
							if(fzModel.getXqksrq()!=null || fzModel.getXqjsrq()!=null){
								Element xqqzrq=new Element ("XQQZRQ").setAttribute("nameCN","刑期起止日期");
								fz.addContent(xqqzrq);
								if(fzModel.getXqksrq()!=null){
									Element xqksrq=new Element ("XQKSRQ").setAttribute("value",fzModel.getXqksrq()); 
									xqksrq.setAttribute("nameCN","刑期开始日期");
									xqqzrq.addContent(xqksrq);
								}
								if(fzModel.getXqjsrq()!=null){
									Element xqjsrq=new Element ("XQJSRQ").setAttribute("value",fzModel.getXqjsrq()); 
									xqjsrq.setAttribute("nameCN","刑期结束日期");
									xqqzrq.addContent(xqjsrq);
								}
							}
							if(fzModel.getXqzdbf()!=null){
								Element xqzdbf=new Element ("XQZDBF").setAttribute("value",fzModel.getXqzdbf()); 
								xqzdbf.setAttribute("nameCN","刑期折抵办法");
								fz.addContent(xqzdbf);
							}
							if(fzModel.getMzhwzsf()!=null){
								Element xqzdbf=new Element ("MZHWZSF").setAttribute("value",fzModel.getMzhwzsf()); 
								xqzdbf.setAttribute("nameCN","免罪或无罪释放");
								fz.addContent(xqzdbf);
							}
							if(fzModel.getSzbf()!=null){
								Element xqzdbf=new Element ("SZBF").setAttribute("value",fzModel.getSzbf()); 
								xqzdbf.setAttribute("nameCN","数罪并罚");
								fz.addContent(xqzdbf);
							}
							if(fzModel.getHblx()!=null){
								Element xqzdbf=new Element ("HBLX").setAttribute("value",fzModel.getHblx()); 
								xqzdbf.setAttribute("nameCN","合并量刑");
								fz.addContent(xqzdbf);
							}
							//原罪
							if(fzModel.getYzpf()!=null &&fzModel.getYzpf().size()>0 ){
								Element yzpjjg=new Element ("YZPJJG").setAttribute("nameCN","原罪判决结果");
								fz.addContent(yzpjjg);
								for(PfModel yzpf:fzModel.getYzpf()){

									Element yz=new Element ("YZPF").setAttribute("value",yzpf.getPfnr()); 
									yz.setAttribute("nameCN","原罪判罚");
									yzpjjg.addContent(yz);


									if(yzpf.getZm()!=null){
										Element zm=new Element ("YZZM").setAttribute("value",yzpf.getZm().getZm()); 
										zm.setAttribute("nameCN","罪名");
										yz.addContent(zm);
									}
									if(yzpf.getZx()!=null){
										Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
										yz.addContent(zx);
										if(yzpf.getZx().getZxlb()!=null){
											Element zxlb=new Element ("ZXLB").setAttribute("value",yzpf.getZx().getZxlb()); 
											zxlb.setAttribute("nameCN","主刑类别");
											zx.addContent(zxlb);
										}
										if(yzpf.getZx().getZxxq()!=null){
											Element zxlb=new Element ("ZXQX").setAttribute("value",yzpf.getZx().getZxxq()); 
											zxlb.setAttribute("nameCN","主刑期限");
											zx.addContent(zxlb);
										}
									}
									if(yzpf.getFjxList()!=null){
										for(FjxModel fjx:yzpf.getFjxList()){
											Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
											yz.addContent(fj);
											if(fjx.getLb()!=null){
												Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
												lb.setAttribute("nameCN","附加刑类别");
												fj.addContent(lb);
												if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
													if(fjx.getSe()!=null){
														Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
														se.setAttribute("nameCN","数额");
														lb.addContent(se);
													}
													if(fjx.getBz()!=null){
														Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
														bz.setAttribute("nameCN","币种");
														lb.addContent(bz);
													}
												}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
													if(fjx.getQx()!=null){
														Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
														qx.setAttribute("nameCN","期限");
														lb.addContent(qx);
													}
												}
											}
										}
									}
									if(yzpf.getHx()!=null){
										Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
										yz.addContent(hx);
										if(yzpf.getHx().getZxlb()!=null){
											Element lb=new Element ("HXLB").setAttribute("value",yzpf.getHx().getZxlb()); 
											lb.setAttribute("nameCN","缓刑类别");
											hx.addContent(lb);
										}
										if(yzpf.getHx().getZxxq()!=null){
											Element lb=new Element ("QX").setAttribute("value",yzpf.getHx().getZxxq()); 
											lb.setAttribute("nameCN","缓刑期限");
											hx.addContent(lb);
										}
									}
								}
							}
						}
					}
				}
			   //分组结束
				if(!StringUtil.isBlank(pjjgModel.getDfdmspccl())){
					Element ysxsbf=new Element ("DFDMSPCDCL").setAttribute("value",pjjgModel.getDfdmspccl()); 
					ysxsbf.setAttribute("nameCN","对附带民事赔偿的处理");
					pjjg.addContent(ysxsbf);
				}
				if(pjjgModel.getFdmspjfzModel()!=null && !StringUtil.isBlank(pjjgModel.getFdmspjfzModel().getNr())){
					Element ysxsbf=new Element ("FDMSPJJGFZ").setAttribute("value",pjjgModel.getFdmspjfzModel().getNr()); 
					ysxsbf.setAttribute("nameCN","附带民事判决结果分组");
					pjjg.addContent(ysxsbf);
					if(pjjgModel.getFdmspjfzModel().getBcje()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getBcje()){
							Element je=new Element ("PCJE").setAttribute("value",s); 
							je.setAttribute("nameCN","赔偿金额");
							ysxsbf.addContent(je);
						}
					}
					if(pjjgModel.getFdmspjfzModel().getBpcr()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getBpcr()){
							Element je=new Element ("BPCR").setAttribute("value",s); 
							je.setAttribute("nameCN","被赔偿人");
							ysxsbf.addContent(je);
						}
					}
					if(pjjgModel.getFdmspjfzModel().getPcr()!=null){
						for(String s:pjjgModel.getFdmspjfzModel().getPcr()){
							Element je=new Element ("PCR").setAttribute("value",s); 
							je.setAttribute("nameCN","赔偿人");
							ysxsbf.addContent(je);
						}
					}
				}
//				民事分组结束
				if(!StringUtil.isBlank(pjjgModel.getKssz())){
					Element ysxsbf=new Element ("KSSZ").setAttribute("value",pjjgModel.getKssz()); 
					ysxsbf.setAttribute("nameCN","可上诉至");
					pjjg.addContent(ysxsbf);
				}
				if(!StringUtil.isBlank(pjjgModel.getSsqx())){
					Element ysxsbf=new Element ("SSQX").setAttribute("value",pjjgModel.getSsqx()); 
					ysxsbf.setAttribute("nameCN","上诉期限");
					pjjg.addContent(ysxsbf);
				}
				if(!StringUtil.isBlank(pjjgModel.getSstjcl())){
					Element ysxsbf=new Element ("SSTJCL").setAttribute("value",pjjgModel.getSstjcl()); 
					ysxsbf.setAttribute("nameCN","上诉提交材料");
					pjjg.addContent(ysxsbf);
				}
				if(pjjgModel.getCsrjh()!=null && pjjgModel.getCsrjh().size()>0){
					Element ysxsbf=new Element ("CSRJH").setAttribute("nameCN","撤诉人集合");
					pjjg.addContent(ysxsbf);
					for(String s:pjjgModel.getCsrjh()){
						Element csr=new Element ("CSR").setAttribute("value",s); 
						csr.setAttribute("nameCN","撤诉人");
						ysxsbf.addContent(csr);
					}
				}
				if(!StringUtil.isBlank(pjjgModel.getCslx())){
					Element ysxsbf=new Element ("CSLX").setAttribute("value",pjjgModel.getCslx()); 
					ysxsbf.setAttribute("nameCN","撤诉类型");
					pjjg.addContent(ysxsbf);
				}
			}
			// 使xml文件 缩进效果  
			Format format = Format.getPrettyFormat();  
			XMLOutputter XMLOut = new XMLOutputter(format);  
			XMLOut.output(Doc, new FileOutputStream(outputpath+"\\"+filename+".xml"));  	
		}else{
//			fileUtil.fileCopy(path, filename, specialpath+"qwspecial", filename);
		}

	}  
	
}
