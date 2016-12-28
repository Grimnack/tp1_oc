import matplotlib.pyplot as plt
import numpy as np

plt.xlim([0,125])
# plt.ylim([0,1600000])
plt.xlabel("les instances")
plt.ylabel("les scores")


def lecture(pathname) :
    '''
    Ouvre un fichier et renvoie la liste des eval et la liste des temps cpu
    '''
    fichier = open(pathname,'r')
    lesEvals = []
    lesTemps = []
    for line in fichier :
        couple = line.split(";")
        lesEvals.append(float(couple[0]))
        lesTemps.append(float(couple[1]))
    fichier.close()
    return (lesEvals,lesTemps)

def loadBest(pathname) :
    '''
    Charge le fichier de best score et renvoie la liste des scores
    '''
    fichier = open(pathname,'r')
    lesBests = []
    for line in fichier :
        chaine = line.replace(" ","")
        chaine = chaine.replace("\n","")
        if chaine != "" :
            lesBests.append(float(chaine))
    fichier.close()
    # print(lesBests)
    return lesBests

def traceEvals(pathname) :
    (lesEvals,lesTemps) = lecture(pathname)
    lesBests = loadBest ("src/test/wtbest100b.txt")
    x = []
    x2 = []
    for i in range(len(lesEvals)) :
        x.append(i)
    for i in range(len(lesBests)) :
        x2.append(i)
    plt.bar(x,lesEvals,color="#87CEFA")
    plt.bar(x2,lesBests,color="red")
    plt.show()

def traceStandardDeviation(pathname) :
    (lesEvals,lesTemps) = lecture(pathname)
    lesBests = loadBest("src/test/wtbest100b.txt")
    lesBests = lesBests[:-1]
    # pourcentages = [] # La liste des pourcentages de deviation
    # for i in range(len(lesEvals)) :
    #     x.append(i)
    #     if lesBests[i] != 0 :
    #         pourcentages.append((lesEvals[i]-lesBests[i])*100/lesBests[i])
    #     else :
    #         pourcentages.append(lesEvals[i])
    # plt.bar(x,pourcentages,color="#87CEFA")
    # plt.show()
    return np.std(np.array([lesEvals,lesBests]))

def traceTableauDeviation(algo,variantes,init) :
    choices = ["first" , "best"]
    latex = "\\begin{tabular}{| c | c | c | c |}\n"
    latex += "\\hline\nselect & voisinages & init & standard deviation \\\\\n"
    for choice in choices :
        for variante in variantes :
            for initiation in init :
                latex += "\\hline\n"+choice+" & "+variante+" & "+initiation+" & "+str(traceStandardDeviation(algo+"_"+choice+"_"+variante+"_"+initiation+".stat"))+"\\\\\n"
    latex += "\\hline\n\\end{tabular}"
    print(latex)                

# traceEvals("random.stat")
# traceEvals("edd.stat")
# traceEvals("mdd.stat")
# traceEvals("hc_first_swap_edd.stat")
# traceStandardDeviation("random.stat")
# print(traceStandardDeviation("vnd_best_ech-ins-swap_edd.stat"))
# print(traceStandardDeviation("vnd_best_ech-ins-swap_mdd.stat"))
# print(traceStandardDeviation("ilsVND_best_ech-ins-swap_edd.stat"))


# traceTableauDeviation("vnd",["ech-ins-swap","ech-swap-ins"],["edd","mdd","rnd"])
# traceTableauDeviation("pvnd",["ech-ins-swap","ech-swap-ins"],["edd","mdd","rnd"])

# traceTableauDeviation("hc",["insert","simple","swap"],["edd","mdd","rnd"])
# traceTableauDeviation("ilsVND",["ech-ins-swap"],["edd","mdd","rnd"])
traceTableauDeviation("ils2optVND",["ech-ins-swap"],["edd","mdd","rnd"])

def vitesseMoyenne(algo,variantes,init) :
    pass
