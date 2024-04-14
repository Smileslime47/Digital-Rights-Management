package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class License extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161091538038061091583398101604081905261002f916100b8565b600180546001600160a01b039094166001600160a01b031994851617905560028054600380546001600160401b039485166001600160401b031990911617905591909216600160a01b0283166001600160e01b031990911617331790556000805490911630179055610109565b80516001600160401b03811681146100b357600080fd5b919050565b6000806000606084860312156100cd57600080fd5b83516001600160a01b03811681146100e457600080fd5b92506100f26020850161009c565b91506101006040850161009c565b90509250925092565b6107fd806101186000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806382678dd61461003b578063bc8018b114610059575b600080fd5b610043610061565b604051610050919061050a565b60405180910390f35b610043610071565b606061006c306101e2565b905090565b6040805180820190915260018152607b60f81b60208201526000546060919081906100a4906001600160a01b03166101e2565b6040516020016100b592919061053d565b60408051601f1981840301815291905260015490915081906100df906001600160a01b03166101e2565b6040516020016100f092919061058f565b60408051601f19818403018152919052600254909150819061011a906001600160a01b03166101e2565b60405160200161012b9291906105e2565b60408051808303601f19018152919052600254909150819061015d90600160a01b900467ffffffffffffffff166103c3565b60405160200161016e92919061061a565b60408051601f1981840301815291905260035490915081906101999067ffffffffffffffff166103c3565b6040516020016101aa929190610671565b6040516020818303038152906040529050806040516020016101cc91906106c9565b60408051601f1981840301815291905292915050565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b8160008151811061024d5761024d610704565b60200101906001600160f81b031916908160001a905350600f60fb1b8160018151811061027c5761027c610704565b60200101906001600160f81b031916908160001a90535060005b60148110156103ba57826004856102ae84600c610730565b602081106102be576102be610704565b1a60f81b6001600160f81b031916901c60f81c60ff16815181106102e4576102e4610704565b01602001516001600160f81b031916826102ff836002610749565b61030a906002610730565b8151811061031a5761031a610704565b60200101906001600160f81b031916908160001a905350828461033e83600c610730565b6020811061034e5761034e610704565b825191901a600f1690811061036557610365610704565b01602001516001600160f81b03191682610380836002610749565b61038b906003610730565b8151811061039b5761039b610704565b60200101906001600160f81b031916908160001a905350600101610296565b50949350505050565b6060816000036103ea5750506040805180820190915260018152600360fc1b602082015290565b8160005b811561041457806103fe81610760565b915061040d9050600a83610779565b91506103ee565b60008167ffffffffffffffff81111561042f5761042f6106ee565b6040519080825280601f01601f191660200182016040528015610459576020820181803683370190505b509050815b85156103ba5761046f60018261079b565b9050600061047e600a88610779565b61048990600a610749565b610493908861079b565b61049e9060306107ae565b905060008160f81b9050808484815181106104bb576104bb610704565b60200101906001600160f81b031916908160001a9053506104dd600a89610779565b9750505061045e565b60005b838110156105015781810151838201526020016104e9565b50506000910152565b60208152600082518060208401526105298160408501602087016104e6565b601f01601f19169190910160400192915050565b6000835161054f8184602088016104e6565b671130b23239111d1160c11b90830190815283516105748160088401602088016104e6565b61088b60f21b60089290910191820152600a01949350505050565b600083516105a18184602088016104e6565b68113934b3b43a111d1160b91b90830190815283516105c78160098401602088016104e6565b61088b60f21b60099290910191820152600b01949350505050565b600083516105f48184602088016104e6565b681137bbb732b9111d1160b91b90830190815283516105c78160098401602088016104e6565b6000835161062c8184602088016104e6565b6c1134b9b9bab2aa34b6b2911d1160991b908301908152835161065681600d8401602088016104e6565b61088b60f21b600d9290910191820152600f01949350505050565b600083516106838184602088016104e6565b6d1132bc3834b932aa34b6b2911d1160911b90830190815283516106ae81600e8401602088016104e6565b61088b60f21b600e9290910191820152601001949350505050565b600082516106db8184602087016104e6565b607d60f81b920191825250600101919050565b634e487b7160e01b600052604160045260246000fd5b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b808201808211156107435761074361071a565b92915050565b80820281158282048414176107435761074361071a565b6000600182016107725761077261071a565b5060010190565b60008261079657634e487b7160e01b600052601260045260246000fd5b500490565b818103818111156107435761074361071a565b60ff81811683821601908111156107435761074361071a56fea2646970667358221220b4cd68c9eaa8790c295788e2aff80f919b1c95ed58b03de8979225f8347f98e264736f6c63430008190033";

    public static final String FUNC_GETKEY = "getKey";

    public static final String FUNC_SERIALIZE = "serialize";

    @Deprecated
    protected License(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getKey() {
        final Function function = new Function(FUNC_GETKEY, 
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
    public static License load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static License load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _right, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _right), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _right, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _right), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _right, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _right), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _right, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _right), 
                new org.web3j.abi.datatypes.generated.Uint64(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint64(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
