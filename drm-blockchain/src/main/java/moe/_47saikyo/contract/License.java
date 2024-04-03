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
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610a0c380380610a0c83398101604081905261002f916100c5565b600061003b848261023a565b5060018054600160a01b6001600160a81b03199091163360ff60a01b19161717600160a81b600160e81b03191663ffffffff938416600160a81b0217905560028054919092166001600160401b0319909116179055506102f9565b634e487b7160e01b600052604160045260246000fd5b805163ffffffff811681146100c057600080fd5b919050565b6000806000606084860312156100da57600080fd5b83516001600160401b03808211156100f157600080fd5b818601915086601f83011261010557600080fd5b81518181111561011757610117610096565b604051601f8201601f19908116603f0116810190838211818310171561013f5761013f610096565b8160405282815260209350898484870101111561015b57600080fd5b600091505b8282101561017d5784820184015181830185015290830190610160565b60008484830101528097505050506101968187016100ac565b935050506101a6604085016100ac565b90509250925092565b600181811c908216806101c357607f821691505b6020821081036101e357634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610235576000816000526020600020601f850160051c810160208610156102125750805b601f850160051c820191505b818110156102315782815560010161021e565b5050505b505050565b81516001600160401b0381111561025357610253610096565b6102678161026184546101af565b846101e9565b602080601f83116001811461029c57600084156102845750858301515b600019600386901b1c1916600185901b178555610231565b600085815260208120601f198616915b828110156102cb578886015182559484019460019091019084016102ac565b50858210156102e95787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b610704806103086000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c8063bc8018b114610030575b600080fd5b61003861004e565b604051610045919061043e565b60405180910390f35b600154606090600090610069906001600160a01b0316610116565b600154600160a01b900460ff1661009d576040518060400160405280600581526020016466616c736560d81b8152506100bb565b604051806040016040528060048152602001637472756560e01b8152505b6001546100d890600160a81b900467ffffffffffffffff166102f7565b6002546100ee9067ffffffffffffffff166102f7565b60405160200161010295949392919061048d565b604051602081830303815290604052905090565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b816000815181106101815761018161060b565b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106101b0576101b061060b565b60200101906001600160f81b031916908160001a90535060005b60148110156102ee57826004856101e284600c610637565b602081106101f2576101f261060b565b1a60f81b6001600160f81b031916901c60f81c60ff16815181106102185761021861060b565b01602001516001600160f81b03191682610233836002610650565b61023e906002610637565b8151811061024e5761024e61060b565b60200101906001600160f81b031916908160001a905350828461027283600c610637565b602081106102825761028261060b565b825191901a600f169081106102995761029961060b565b01602001516001600160f81b031916826102b4836002610650565b6102bf906003610637565b815181106102cf576102cf61060b565b60200101906001600160f81b031916908160001a9053506001016101ca565b50949350505050565b60608160000361031e5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115610348578061033281610667565b91506103419050600a83610680565b9150610322565b60008167ffffffffffffffff811115610363576103636105f5565b6040519080825280601f01601f19166020018201604052801561038d576020820181803683370190505b509050815b85156102ee576103a36001826106a2565b905060006103b2600a88610680565b6103bd90600a610650565b6103c790886106a2565b6103d29060306106b5565b905060008160f81b9050808484815181106103ef576103ef61060b565b60200101906001600160f81b031916908160001a905350610411600a89610680565b97505050610392565b60005b8381101561043557818101518382015260200161041d565b50506000910152565b602081526000825180602084015261045d81604085016020870161041a565b601f01601f19169190910160400192915050565b6000815161048381856020860161041a565b9290920192915050565b6d3d913934b3b43a2730b6b2911d1160911b815285546000908190600181811c90808316806104bd57607f831692505b602080841082036104dc57634e487b7160e01b86526022600452602486fd5b8180156104f0576001811461050b5761053e565b60ff198616600e8a0152600e85151586028a0101965061053e565b60008e81526020902060005b868110156105335781548b8201600e0152908501908301610517565b5050600e858a010196505b5050505050506105e96105dc6105d66105bc6105b661059d61059761057d610577896a11161137bbb732b9111d1160a91b8152600b0190565b8f610471565b6d11161130bb30b4b630b13632911d60911b8152600e0190565b8c610471565b6c161134b9b9bab2aa34b6b2911d60991b8152600d0190565b89610471565b6d161132bc3834b932aa34b6b2911d60911b8152600e0190565b86610471565b607d60f81b815260010190565b98975050505050505050565b634e487b7160e01b600052604160045260246000fd5b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b8082018082111561064a5761064a610621565b92915050565b808202811582820484141761064a5761064a610621565b60006001820161067957610679610621565b5060010190565b60008261069d57634e487b7160e01b600052601260045260246000fd5b500490565b8181038181111561064a5761064a610621565b60ff818116838216019081111561064a5761064a61062156fea264697066735822122070beee7daf5c0bfb5ae233656de5d1f37081c245fc1a9b2ad447b3bc3bc3d74964736f6c63430008190033";

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

    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
