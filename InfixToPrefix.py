'''
    2023-02-16(Thu) SoheeJung
    Infix 형태의 Predicate을 Prefix 형태로 변환해주는 코드
    reference : https://stackoverflow.com/questions/71076158/python-program-to-convert-infix-to-prefix-notation

    2023-02-17 수정해야 하는 부분 : 괄호의 출력이 제대로 진행되지 않음. -> 해당 부분은 수작업으로 실험 진행
'''
import re

class Stack: 
    # LIFO Stack implementation using a Python list as underlying storage.
    def __init__(self): 
        self.data =[]   

    def __len__(self):  
        return len(self.data)

    def is_empty(self): 
        return len(self.data)==0

    def push(self, e):  
        self.data.append(e) 

    def top(self):       
        return self.data[-1] 

    def pop(self):      
        return self.data.pop()

def operatorpriority(x):
    if x in "+-><=&|":
        return 1
    elif x in "*/":
        return 2
    elif x in "^!":
        return 3
    return 0

def InfixToPrefix(A):
    # Converts Infix to Prefix Notation
    stack = Stack()
    A = '(' + A + ')'
    output = ""
    for c in A[::-1]:
        if c.isnumeric() or c.isalpha() or c=='.' or c=='_' :
            output+=c
        elif c == ")":
            stack.push(c)
            output+=")"
        elif c in "+-*/^><=|&!":
            if c == "^":
                while operatorpriority(c) <= operatorpriority(stack.top()):
                    output+=stack.pop()
            else:
                while operatorpriority(c) < operatorpriority(stack.top()):
                    output+=stack.pop()           
            stack.push(c)
        elif c == "(":
            while not stack.is_empty():
                c1 = stack.pop()
                if c1 == ')':
                    break
                output+=c1
    while not stack.is_empty():
        output+=stack.pop()

    return output

def preprocessing(predicate : str) : 
    # convert from "and / or" to "& / |"
    predicate = predicate.replace("and", "&")
    predicate = predicate.replace("or", "|")
    # convert from "<= / >=" to "=< / =>"
    predicate = predicate.replace("<=", "=<")
    predicate = predicate.replace(">=", "=>")
    # convert from "==" to "="
    predicate = predicate.replace("==", "=")
    # not operation이 있을 경우, 해당 식 양 옆으로 괄호를 붙여준다.
    pattern = "!\([^()]*\)"
    pattern_compile = re.compile(pattern)
    result = pattern_compile.findall(predicate)
    for not_predicate in result :
        predicate = predicate.replace(not_predicate, "(" + str(not_predicate) + ")")

    return predicate

def postprocessing(predicate : str) :
    # convert from "& / |" to "and / or"
    predicate = predicate.replace("&", "and")
    predicate = predicate.replace("|", "or")
    # convert from "!" to "not"
    predicate = predicate.replace("!", "not")

    return predicate

def InsertSpace(prefix : str, parameters : list()) :
    operator_list = ["or", "and", "not", ">=", ">", "<=", "<", "=", ")"]
    for operator in operator_list : 
        prefix = prefix.replace(operator, operator + " ")
    for character in prefix :
        if character.isnumeric() :
            prefix = prefix.replace(character, character + " ")
    for parameter in parameters :
        prefix = prefix.replace(parameter, parameter + " ")
    # 다중 공백 없애주기
    prefix = re.sub(r"\s+", " ", prefix)
    # "> =" , "< =", " )" 이렇게 처리된 부분들은 ">=", "<=", ")" 로 처리해주기
    prefix = prefix.replace(" )", ")")
    prefix = prefix.replace("> =", ">=")
    prefix = prefix.replace("< =", "<=")
    # print(prefix)
    return prefix

def InsertParenthesis(prefix : str) :
    output = ""
    call_stack = Stack();
    # 공백 기준으로 prefix 분리해서 list에 저장
    prefix_str_list = prefix.split()

    for i in range(len(prefix_str_list)) : 
        # and, or, not을 만나면 해당 string에 "(" 추가해주고
        if prefix_str_list[i] in ["and", "or", "not"] :
            call_stack.push(prefix_str_list[i])
            output += "(" + prefix_str_list[i] + " "
        # operator 만나면 "(" 추가해주고, 해당 operator 포함 2번 뒤의 str에 ")" 추가해주기
        elif prefix_str_list[i] in ["<=", ">=", "<", ">", "="] :
            output += "(" + prefix_str_list[i] + " "
        else :
            
            output += prefix_str_list[i] + " "
                    
    return output

input_predicate = '((!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and (3 == rt_input.obsDistance_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and !(3 == rt_input.obsDistance_0) and (1 == (rt_input.obsDistance_0 - 3))) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and !(3 == rt_input.obsDistance_0) and !(1 == (rt_input.obsDistance_0 - 3)) and !(rt_input.obsDistance_1 >= 7) and (1 == rt_input.obsDistance_3) and (1 == rt_input.obsDistance_2) and !(3 >= rt_input.obsDistance_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and !(3 == rt_input.obsDistance_0) and !(1 == (rt_input.obsDistance_0 - 3)) and !(rt_input.obsDistance_1 >= 7) and (1 == rt_input.obsDistance_3) and !(1 == rt_input.obsDistance_2)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and !(3 == rt_input.obsDistance_0) and !(1 == (rt_input.obsDistance_0 - 3)) and !(rt_input.obsDistance_1 >= 7) and !(1 == rt_input.obsDistance_3) and (rt_input.obsDistance_0 >= 12) and (rt_input.obsDistance_3 > rt_input.obsDistance_1)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and (1 >= rt_state.zone_1) and !(3 == rt_input.obsDistance_0) and !(1 == (rt_input.obsDistance_0 - 3)) and !(rt_input.obsDistance_1 >= 7) and !(1 == rt_input.obsDistance_3) and !(rt_input.obsDistance_0 >= 12) and !(rt_input.obsDistance_3 >= rt_input.obsDistance_2) and (rt_input.obsDistance_2 > rt_input.obsDistance_1) and (7 >= rt_input.obsDistance_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and !(1 >= rt_state.zone_1) and (rt_state.speed_status == Stop) and (rt_input.obsDistance_0 <= 4) and !(rt_state.zone_1 >= rt_input.obsDistance_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and !(1 >= rt_state.zone_1) and (rt_state.speed_status == Stop) and !(rt_input.obsDistance_0 <= 4) and (rt_input.obsDistance_2 <= 4) and (rt_input.obsDistance_2 > rt_input.obsDistance_3) and !(rt_state.zone_0 >= rt_input.obsDistance_2) and (rt_state.zone_1 >= rt_input.obsDistance_3)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and !(1 >= rt_state.zone_1) and (rt_state.speed_status == Stop) and !(rt_input.obsDistance_0 <= 4) and (rt_input.obsDistance_2 <= 4) and !(rt_input.obsDistance_2 > rt_input.obsDistance_3) and (rt_input.obsDistance_3 >= 7) and !(rt_input.obsDistance_2 > rt_state.zone_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and !(1 >= rt_state.zone_1) and !(rt_state.speed_status == Stop) and !(rt_input.obsDistance_0 <= 4) and (rt_input.obsDistance_2 <= 4) and !(3 == rt_input.obsDistance_3) and (rt_input.obsDistance_3 >= rt_input.obsDistance_2) and (rt_input.obsDistance_3 >= 6) and (rt_state.zone_0 >= rt_input.obsDistance_2)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and (rt_input.obsDistance_1 > rt_state.zone_0) and !(1 >= rt_state.zone_1) and !(rt_state.speed_status == Stop) and !(rt_input.obsDistance_0 <= 4) and (rt_input.obsDistance_2 <= 4) and !(3 == rt_input.obsDistance_3) and !(rt_input.obsDistance_3 >= rt_input.obsDistance_2) and (rt_input.obsDistance_2 > rt_state.zone_0)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and !(rt_input.obsDistance_1 > rt_state.zone_0) and (rt_input.obsDistance_3 >= rt_input.obsDistance_2) and (rt_input.obsDistance_0 > 2)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and !(rt_input.obsDistance_1 > rt_state.zone_0) and !(rt_input.obsDistance_3 >= rt_input.obsDistance_2) and (rt_input.obsDistance_2 > 2)) or (!(3 == rt_input.obsDistance_1) and !(1 == (rt_input.obsDistance_1 - 3)) and !(rt_input.obsDistance_1 > rt_state.zone_0) and !(rt_input.obsDistance_3 >= rt_input.obsDistance_2) and !(rt_input.obsDistance_2 > 2) and (rt_input.obsDistance_0 > 2)))'
result = InfixToPrefix(preprocessing(input_predicate))[::-1]
print(InsertParenthesis(InsertSpace(postprocessing(result), ["rt_input.obsDistance_0", "rt_input.obsDistance_1", "rt_input.obsDistance_2", "rt_input.obsDistance_3", "rt_state.zone_0", "rt_state.zone_1", "rt_state.speed_status", "Stop", "Fast_speed"])))