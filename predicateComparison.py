'''
2023-11-27(Mon)
author : Sohee Jung
name : predicateComparison.py
compare origin predicate to generated predicate from EUsolver.
'''

from termcolor import colored
import subprocess

def gen_compare_predicates(origin_predicate, generated_predicate) :
    for fileIndex in range(40) :
        file = open("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/paper-experiment-code/predicateCompare" + str(fileIndex + 1) + ".c", 'w')

        file.write("#include <stdio.h>\n")
        file.write("#include <stdlib.h>\n")
        file.write("#include <stdbool.h>\n")
        if fileIndex == 0 :
            file.write("#include \"common/model_prime.h\"\n\n")
        elif fileIndex > 0 and fileIndex < 9 :
            file.write("#include \"common/model_gcd.h\"\n\n")
        elif fileIndex >= 9 and fileIndex < 18 :
            file.write("#include \"common/model_second.h\"\n\n")
        elif fileIndex >= 18 and fileIndex < 22 :
            file.write("#include \"common/model_triangle.h\"\n\n")
        else :
            file.write("#include \"common/model_CA.h\"\n\n")
 
        file.write("INPUT rt_input;\n")
        file.write("STATE rt_state;\n")
        file.write("OUTPUT rt_output;\n\n")

        file.write("int main() {\n")

        originPred = origin_predicate[fileIndex]
        generatedPred = generated_predicate[fileIndex]
        
        originPred = originPred.replace("obsDistance_0", "obsDistance[0]")
        originPred = originPred.replace("obsDistance_1", "obsDistance[1]")
        originPred = originPred.replace("obsDistance_2", "obsDistance[2]")
        originPred = originPred.replace("obsDistance_3", "obsDistance[3]")
        originPred = originPred.replace("zone_0", "zone[0]")
        originPred = originPred.replace("zone_1", "zone[1]")
        originPred = originPred.replace("and", "&&")
        originPred = originPred.replace("or", "||")

        generatedPred = generatedPred.replace("obsDistance_0", "obsDistance[0]")
        generatedPred = generatedPred.replace("obsDistance_1", "obsDistance[1]")
        generatedPred = generatedPred.replace("obsDistance_2", "obsDistance[2]")
        generatedPred = generatedPred.replace("obsDistance_3", "obsDistance[3]")
        generatedPred = generatedPred.replace("zone_0", "zone[0]")
        generatedPred = generatedPred.replace("zone_1", "zone[1]")
        generatedPred = generatedPred.replace("and", "&&")
        generatedPred = generatedPred.replace("or", "||")
        
        file.write("\tbool originPred = " + originPred + ";\n")
        file.write("\tbool generatedPred = " + generatedPred + ";\n\n")

        file.write("\tassert(originPred == generatedPred);\n")
        file.write("}")

        file.close()

def get_predicate(filePath) :
    file = open(filePath, 'r')
    lines = file.readlines()
    file.close()

    # predicate collect list
    predicateList = []
    count = 0
    for line in lines :
        line = line.rstrip()
        # if line contain "[predicate*]", then continue.
        # if line don't contain anythine, then continue.
        if "[predicate" in line or line == "":
            continue
        # if line don't contain "[preicate*]", then collect predicate.
        else :
            predicateList.append(line)
    
    return predicateList

def run_predicate_comparison() :
    path = '/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/paper-experiment-code/'
    resultFilePath = path + 'comparison_result.txt'

    for i in range(40) :
        p = subprocess.Popen('cbmc ' + path + 'predicateCompare' + str(i+1) + '.c --unwind 10 > ' + path + 'output_comparison.txt', stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
        try:
            output,o_err = p.communicate()
            p.kill()
            if o_err and 'error' in str(o_err):
                print(o_err)
                exit(0)
        except subprocess.TimeoutExpired:
            p.kill()
            print(colored("[WARNING] TIMEOUT",'magenta'))
        except subprocess.CalledProcessError:
            p.kill()
            print(colored("[ERROR] FAILED",'magenta'))
        
        file = open(path + 'output_comparison.txt', 'r')
        lines = file.readlines()
        file.close()

        resultFile = open(resultFilePath, 'a')
        resultFile.write('predicate' + str(i+1) +' result : ')
        
        if any(["VERIFICATION SUCCESSFUL" in line for line in lines]) :
            resultFile.write("SUCCESS\n")
        else :
            resultFile.write("FAILURE\n")

# main
originFile = '/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/paper-expriment/origin_predicate.txt'
generatedFile = '/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/paper-expriment/final_output_collection_no_space_modify.txt'

originPredList = get_predicate(originFile)
generatedPredList = get_predicate(generatedFile)

gen_compare_predicates(originPredList, generatedPredList)
run_predicate_comparison()