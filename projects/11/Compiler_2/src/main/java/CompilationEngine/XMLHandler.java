package CompilationEngine;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class XMLHandler
{
    public static boolean isNumber(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1)
            {
                Element term = message.get(0);
                List<Element> childrenOfTerm = term.getChildren();
                return (childrenOfTerm.size() == 1 && childrenOfTerm.get(0).getName().equals("integerConstant"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isString(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1)
            {
                Element term = message.get(0);
                List<Element> childrenOfTerm = term.getChildren();
                return (childrenOfTerm.size() == 1 && childrenOfTerm.get(0).getName().equals("stringConstant"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isKeywordConstant(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1)
            {
                Element term = message.get(0);
                List<Element> childrenOfTerm = term.getChildren();
                return (childrenOfTerm.size() == 1 && childrenOfTerm.get(0).getName().equals("keyword"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExpInBraces(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1)
            {
                Element term = message.get(0);
                List<Element> childrenOfTerm = term.getChildren();
                Element symbol = childrenOfTerm.get(0);
                return (symbol.getName().equals("symbol") && symbol.getValue().equals("("));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isVariable(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1)
            {
                Element term = message.get(0);
                List<Element> childrenOfTerm = term.getChildren();
                return (childrenOfTerm.size() == 1 && childrenOfTerm.get(0).getName().equals("identifier"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExpOpExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 3)
            {
                return (message.get(0).getName().equals("term") &&
                        message.get(1).getName().equals("symbol") &&
                        message.get(2).getName().equals("term"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isOpExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            if (doc.getRootElement().getChildren().size() == 1 && message.size() == 2)
            {
                return (message.get(0).getName().equals("symbol") &&
                        message.get(1).getName().equals("term"));
            } else
            {
                return false;
            }
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    public static String getNumberFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element integerConstant = message.get(0).getChild("integerConstant");
            return integerConstant.getValue();
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element stringConstant = message.get(0).getChild("stringConstant");
            return stringConstant.getValue();
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getKeywordConst(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element integerConstant = message.get(0).getChild("keyword");
            return integerConstant.getValue();
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSymbolFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element symbol = message.get(0);
            return symbol.getValue();
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getVariableFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element integerConstant = message.get(0).getChild("identifier");
            return getNameFromIdentifier(integerConstant.getValue());
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String getNameFromIdentifier(String identifierInfo)
    {
        int indexOfComma = identifierInfo.indexOf(",");
        if (indexOfComma != -1)
            return identifierInfo.substring(0, indexOfComma);
        return identifierInfo;
    }


    public static String getNextElementFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element nextTerm = message.get(0).clone();
            Element root = new Element("expression");
            root.addContent(nextTerm);
            Document doc1 = new Document(root);
            return convertXMLtoString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getNextElementFromUnaryExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            doc.getRootElement().getChildren();
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            Element nextTerm = message.get(0).clone();
            Element root = new Element("expression");
            root.addContent(nextTerm);
            Document doc1 = new Document(root);
            return convertXMLtoString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String removeNextElementFromExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            doc.getRootElement().getChildren().remove(0);
            return convertXMLtoString(doc);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String removeNextElementFromUnaryExp(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            doc.getRootElement().getChild("term").getChildren().remove(0);
            return convertXMLtoString(doc);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getExpWithoutBraces(String exp)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(exp));
            List<Element> message = doc.getRootElement().getChildren();
            Element term = message.get(0);
            List<Element> childrenOfTerm = term.getChildren();
            Element expressionInBraces = childrenOfTerm.get(1).clone();
            Document doc1 = new Document(expressionInBraces);
            XMLOutputter xmOut = new XMLOutputter();
            return xmOut.outputString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertXMLtoString(Document doc)
    {
        XMLOutputter xmOut = new XMLOutputter();
        return xmOut.outputString(doc);
    }


    public static boolean isExpListNotEmpty(String expList)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expList));
            List<Element> expressions = doc.getRootElement().getChildren();
            return expressions.size() != 0;
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isThereCommaSeparatedInExpList(String expList)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expList));
            List<Element> expressions = doc.getRootElement().getChildren();
            return expressions.get(0).getValue().equals(",");
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static String getNextExpFromList(String expList)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expList));
            List<Element> message = doc.getRootElement().getChildren();
            Document doc1 = new Document(message.get(0).clone());
            return convertXMLtoString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String removeNextExpFromList(String expList)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expList));
            doc.getRootElement().getChildren().remove(0);
            return convertXMLtoString(doc);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isFunctionCall(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            return message.size() >= 2 && message.get(0).getName().equals("identifier") &&
                    message.get(1).getName().equals("symbol") &&
                    (message.get(1).getValue().equals(".") ||
                            message.get(1).getValue().equals("("));
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isArrayExp(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            return (message.size() >= 2 && message.get(0).getName().equals("identifier") &&
                    message.get(1).getName().equals("symbol") &&
                    message.get(1).getValue().equals("["));
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static String getFuncNameFromFuncCallInExp(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            if (message.get(0).getName().equals("identifier") && message.get(1).getValue().equals("(")) //func(exp)
            {
                return getNameFromIdentifier(message.get(0).getValue());
            } else if (message.get(0).getName().equals("identifier") && message.get(1).getValue().equals(".")) //f1.f2(exp)
            {
                return getNameFromIdentifier(message.get(0).getValue()) + "." + getNameFromIdentifier(message.get(2).getValue());
            }

        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getArrayNameFromExp(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChild("term").getChildren();
            return getNameFromIdentifier(message.get(0).getValue()); //arr[exp]

        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFuncNameFromFuncCall(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            List<Element> message = doc.getRootElement().getChildren();
            if (message.size() == 1 && message.get(0).getName().equals("identifier")) //func(exp)
            {
                return getNameFromIdentifier(message.get(0).getValue());
            } else if (message.get(0).getName().equals("identifier") && message.get(1).getValue().equals(".")) //f1.f2(exp)
            {
                return getNameFromIdentifier(message.get(0).getValue()) + "." + getNameFromIdentifier(message.get(2).getValue());
            }

        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getExpListFromFuncCall(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            Element expList = doc.getRootElement().getChild("term").getChild("expressionList");

            Document doc1 = new Document(expList.clone());
            return convertXMLtoString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String getExpFromArray(String expression)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        try
        {
            Document doc = saxBuilder.build(new StringReader(expression));
            Element exp = doc.getRootElement().getChild("term").getChild("expression");

            Document doc1 = new Document(exp.clone());
            return convertXMLtoString(doc1);
        } catch (IOException | JDOMException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
