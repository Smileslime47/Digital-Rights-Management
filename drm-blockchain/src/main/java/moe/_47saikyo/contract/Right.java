package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class Right extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516112c73803806112c783398101604081905261002f916101b6565b600080546001600160a01b03191630179055600161004d8882610333565b50600361005a8782610333565b50600480546001600160401b0386811668010000000000000000026001600160801b03199092169088161717905560056100948482610333565b5060076100a18382610333565b5060086100ae8282610333565b5050600280546001600160a01b031990811633179091556000805490911630179055506103f2945050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261010357600080fd5b81516001600160401b038082111561011d5761011d6100dc565b604051601f8301601f19908116603f01168101908282118183101715610145576101456100dc565b816040528381526020925086602085880101111561016257600080fd5b600091505b838210156101845785820183015181830184015290820190610167565b6000602085830101528094505050505092915050565b80516001600160401b03811681146101b157600080fd5b919050565b600080600080600080600060e0888a0312156101d157600080fd5b87516001600160401b03808211156101e857600080fd5b6101f48b838c016100f2565b985060208a015191508082111561020a57600080fd5b6102168b838c016100f2565b975061022460408b0161019a565b965061023260608b0161019a565b955060808a015191508082111561024857600080fd5b6102548b838c016100f2565b945060a08a015191508082111561026a57600080fd5b6102768b838c016100f2565b935060c08a015191508082111561028c57600080fd5b506102998a828b016100f2565b91505092959891949750929550565b600181811c908216806102bc57607f821691505b6020821081036102dc57634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561032e576000816000526020600020601f850160051c8101602086101561030b5750805b601f850160051c820191505b8181101561032a57828155600101610317565b5050505b505050565b81516001600160401b0381111561034c5761034c6100dc565b6103608161035a84546102a8565b846102e2565b602080601f831160018114610395576000841561037d5750858301515b600019600386901b1c1916600185901b17855561032a565b600085815260208120601f198616915b828110156103c4578886015182559484019460019091019084016103a5565b50858210156103e25787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b610ec6806104016000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80638493f71f146100675780639cf834cb14610085578063a5b06b3b1461008d578063bc8018b1146100a2578063ccaf0804146100aa578063ff3c1a8f1461010c575b600080fd5b61006f610114565b60405161007c919061085e565b60405180910390f35b61006f6101a6565b6100956101b5565b60405161007c9190610891565b61006f610216565b61010a6100b83660046108de565b600680546001810182556000919091527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f0180546001600160a01b0319166001600160a01b0392909216919091179055565b005b61006f610527565b6060600880546101239061090e565b80601f016020809104026020016040519081016040528092919081815260200182805461014f9061090e565b801561019c5780601f106101715761010080835404028352916020019161019c565b820191906000526020600020905b81548152906001019060200180831161017f57829003601f168201915b5050505050905090565b6060600380546101239061090e565b6060600680548060200260200160405190810160405280929190818152602001828054801561019c57602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116101ef575050505050905090565b6040805180820190915260018152607b60f81b6020820152600054606091908190610249906001600160a01b0316610536565b60405160200161025a929190610948565b604051602081830303815290604052905080600160405160200161027f929190610a37565b60408051601f1981840301815291905260025490915081906102a9906001600160a01b0316610536565b6040516020016102ba929190610a7d565b60405160208183030381529060405290508060036040516020016102df929190610ad0565b60408051601f19818403018152919052600454909150819061030a9067ffffffffffffffff16610717565b60405160200161031b929190610b0f565b60408051808303601f1901815291905260045490915081906103529068010000000000000000900467ffffffffffffffff16610717565b604051602001610363929190610b66565b6040516020818303038152906040529050806005604051602001610388929190610bbe565b60405160208183030381529060405290508060076040516020016103ad929190610bf6565b60405160208183030381529060405290508060086040516020016103d2929190610c2b565b6040516020818303038152906040529050806040516020016103f49190610c60565b604051602081830303815290604052905060005b6006548110156104ff57816006828154811061042657610426610c90565b60009182526020822001546040805163bc8018b160e01b815290516001600160a01b039092169263bc8018b1926004808401938290030181865afa158015610472573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261049a9190810190610cbc565b6040516020016104ab929190610d69565b60408051601f198184030181529190526006549092506104cd90600190610dae565b8110156104f757816040516020016104e59190610dc7565b60405160208183030381529060405291505b600101610408565b50806040516020016105119190610dec565b60408051601f1981840301815291905292915050565b6060600180546101239061090e565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b816000815181106105a1576105a1610c90565b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106105d0576105d0610c90565b60200101906001600160f81b031916908160001a90535060005b601481101561070e578260048561060284600c610e12565b6020811061061257610612610c90565b1a60f81b6001600160f81b031916901c60f81c60ff168151811061063857610638610c90565b01602001516001600160f81b03191682610653836002610e25565b61065e906002610e12565b8151811061066e5761066e610c90565b60200101906001600160f81b031916908160001a905350828461069283600c610e12565b602081106106a2576106a2610c90565b825191901a600f169081106106b9576106b9610c90565b01602001516001600160f81b031916826106d4836002610e25565b6106df906003610e12565b815181106106ef576106ef610c90565b60200101906001600160f81b031916908160001a9053506001016105ea565b50949350505050565b60608160000361073e5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115610768578061075281610e3c565b91506107619050600a83610e55565b9150610742565b60008167ffffffffffffffff81111561078357610783610ca6565b6040519080825280601f01601f1916602001820160405280156107ad576020820181803683370190505b509050815b851561070e576107c3600182610dae565b905060006107d2600a88610e55565b6107dd90600a610e25565b6107e79088610dae565b6107f2906030610e77565b905060008160f81b90508084848151811061080f5761080f610c90565b60200101906001600160f81b031916908160001a905350610831600a89610e55565b975050506107b2565b60005b8381101561085557818101518382015260200161083d565b50506000910152565b602081526000825180602084015261087d81604085016020870161083a565b601f01601f19169190910160400192915050565b6020808252825182820181905260009190848201906040850190845b818110156108d25783516001600160a01b0316835292840192918401916001016108ad565b50909695505050505050565b6000602082840312156108f057600080fd5b81356001600160a01b038116811461090757600080fd5b9392505050565b600181811c9082168061092257607f821691505b60208210810361094257634e487b7160e01b600052602260045260246000fd5b50919050565b6000835161095a81846020880161083a565b671130b23239111d1160c11b908301908152835161097f81600884016020880161083a565b61088b60f21b60089290910191820152600a01949350505050565b8054600090600181811c90808316806109b457607f831692505b602080841082036109d557634e487b7160e01b600052602260045260246000fd5b8180156109e957600181146109fe57610a2b565b60ff1986168952841515850289019650610a2b565b60008881526020902060005b86811015610a235781548b820152908501908301610a0a565b505084890196505b50505050505092915050565b60008351610a4981846020880161083a565b68113a34ba3632911d1160b91b908301908152610a69600982018561099a565b61088b60f21b815260020195945050505050565b60008351610a8f81846020880161083a565b681137bbb732b9111d1160b91b9083019081528351610ab581600984016020880161083a565b61088b60f21b60099290910191820152600b01949350505050565b60008351610ae281846020880161083a565b75113932b3b4b9ba3930ba34b7b7273ab6b132b9111d1160511b908301908152610a69601682018561099a565b60008351610b2181846020880161083a565b6c1134b9b9bab2aa34b6b2911d1160991b9083019081528351610b4b81600d84016020880161083a565b61088b60f21b600d9290910191820152600f01949350505050565b60008351610b7881846020880161083a565b6d1132bc3834b932aa34b6b2911d1160911b9083019081528351610ba381600e84016020880161083a565b61088b60f21b600e9290910191820152601001949350505050565b60008351610bd081846020880161083a565b6e113232b9b1b934b83a34b7b7111d1160891b908301908152610a69600f82018561099a565b60008351610c0881846020880161083a565b6b113334b632a730b6b2911d1160a11b908301908152610a69600c82018561099a565b60008351610c3d81846020880161083a565b6b113334b632a430b9b4111d1160a11b908301908152610a69600c82018561099a565b60008251610c7281846020870161083a565b6b226c6963656e736573223a5b60a01b920191825250600c01919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052604160045260246000fd5b600060208284031215610cce57600080fd5b815167ffffffffffffffff80821115610ce657600080fd5b818401915084601f830112610cfa57600080fd5b815181811115610d0c57610d0c610ca6565b604051601f8201601f19908116603f01168101908382118183101715610d3457610d34610ca6565b81604052828152876020848701011115610d4d57600080fd5b610d5e83602083016020880161083a565b979650505050505050565b60008351610d7b81846020880161083a565b835190830190610d8f81836020880161083a565b01949350505050565b634e487b7160e01b600052601160045260246000fd5b81810381811115610dc157610dc1610d98565b92915050565b60008251610dd981846020870161083a565b600b60fa1b920191825250600101919050565b60008251610dfe81846020870161083a565b615d7d60f01b920191825250600201919050565b80820180821115610dc157610dc1610d98565b8082028115828204841417610dc157610dc1610d98565b600060018201610e4e57610e4e610d98565b5060010190565b600082610e7257634e487b7160e01b600052601260045260246000fd5b500490565b60ff8181168382160190811115610dc157610dc1610d9856fea2646970667358221220b83a296f974f1c51694e512382d7e5cac6f2fb956257d7cf8805b331e394bff964736f6c63430008190033";

    public static final String FUNC_ADDLICENSE = "addLicense";

    public static final String FUNC_GETFILEHASH = "getFileHash";

    public static final String FUNC_GETLICENSES = "getLicenses";

    public static final String FUNC_GETREGISTRATIONNUMBER = "getRegistrationNumber";

    public static final String FUNC_GETTITLE = "getTitle";

    public static final String FUNC_SERIALIZE = "serialize";

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String _license) {
        final Function function = new Function(
                FUNC_ADDLICENSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getFileHash() {
        final Function function = new Function(FUNC_GETFILEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getLicenses() {
        final Function function = new Function(FUNC_GETLICENSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getRegistrationNumber() {
        final Function function = new Function(FUNC_GETREGISTRATIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getTitle() {
        final Function function = new Function(FUNC_GETTITLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> serialize() {
        final Function function = new Function(FUNC_SERIALIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _title, String _registrationNumber, BigInteger _issueTime, BigInteger _expireTime, String _description, String _fileName, String _fileHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_registrationNumber), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_fileName), 
                new org.web3j.abi.datatypes.Utf8String(_fileHash)));
        return deployRemoteCall(Right.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
